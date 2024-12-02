package src;

import javax.swing.*;

public class NewFolder {

    public NewFolder() {
        showCreateFolderDialog();
    }

    public void showCreateFolderDialog() {
        String title = JOptionPane.showInputDialog("Create New Folder");
        JOptionPane.showMessageDialog(null, "Create Folder " + title + "!");
    }
}
