package src;

import javax.swing.*;

public class NewFolder {

    public NewFolder() {
        showCreateFolderDialog();
    }

    public void showCreateFolderDialog() {
        JOptionPane.showMessageDialog(null, "Create Folder", "New Folder", JOptionPane.INFORMATION_MESSAGE);
    }
}
