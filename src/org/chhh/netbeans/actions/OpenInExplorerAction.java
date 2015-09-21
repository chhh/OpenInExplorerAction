/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chhh.netbeans.actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.util.*;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "View",
        id = "org.chhh.netbeans.actions.OpenInExplorerAction"
)
@ActionRegistration(
        displayName = "#CTL_OpenInExplorerAction"
)
@ActionReference(
        path = "Loaders/folder/any/Actions",
        separatorBefore = 2147483640,
        position = 2147483641
)
@Messages("CTL_OpenInExplorerAction=Open in Explorer")
public final class OpenInExplorerAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        org.openide.util.Lookup lkp = Utilities.actionsGlobalContext();
        DataFolder folder = lkp.lookup(DataFolder.class);
        if (folder != null) {
            FileObject primaryFile = folder.getPrimaryFile();
            File file = FileUtil.toFile(primaryFile);
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                String msg = String.format("Something awful happened when trying to open directory in explorer:\n%s", file.getAbsolutePath());
                NotifyDescriptor nd = new NotifyDescriptor.Message(msg, NotifyDescriptor.INFORMATION_MESSAGE);
                DialogDisplayer.getDefault().notify(nd);
                //Exceptions.printStackTrace(ex);
            }
        }
    }
}
