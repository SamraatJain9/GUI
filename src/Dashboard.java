package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class Dashboard {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JPanel settingsPanel;
    private JSplitPane splitPane;
    private String username = "";
    private JCheckBox rememberLoginBox;
    public boolean rememberUserLogin = false;

    public Dashboard() {
        List<SymbolEntry> symbolEntries = BinaryFile.readFromFile("src/on_disk/secure.bin");
        if (!symbolEntries.isEmpty()) {
            username = symbolEntries.get(0).getUsername();
        }
        frame = new JFrame();
        frame.setTitle("Dashboard");
        label = new JLabel("Hello " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton createFolderButton = new JButton("Create Folder");
        createFolderButton.addActionListener(e -> openCreateFolder());

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> toggleSettingsPanel());

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(createFolderButton);
        panel.add(settingsButton);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));

        JButton usernameLabel = new JButton("Username");
        usernameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        usernameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openUsernameDialog();
            }
        });

        rememberLoginBox = new JCheckBox("Remember Login");
        rememberLoginBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rememberLoginBox.setBounds(100, 150, 50, 50);
        rememberLoginBox.addActionListener(e -> checkBoxMarked(rememberUserLogin));

        settingsPanel.add(rememberLoginBox);
        settingsPanel.add(usernameLabel);

        JButton closeSettings = new JButton("Close");
        closeSettings.addActionListener(e -> toggleSettingsPanel());

        settingsPanel.add(closeSettings);
        settingsPanel.setVisible(false);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, settingsPanel);
        splitPane.setDividerLocation(800);
        splitPane.setEnabled(false);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void toggleSettingsPanel() {
        boolean isSettingsVisible = settingsPanel.isVisible();
        settingsPanel.setVisible(!isSettingsVisible);
        splitPane.setDividerLocation(isSettingsVisible ? 1.0 : 0.8);
    }

    public void openUsernameDialog() {
        String newUsername = JOptionPane.showInputDialog(frame, "Enter your username: ", username);
        if (newUsername != null && !newUsername.trim().isEmpty()) {
            username = newUsername.trim();
            label.setText("Hello " + username);

            List<SymbolEntry> symbolEntries = BinaryFile.readFromFile("src/on_disk/secure.bin");
            if (!symbolEntries.isEmpty()) {
                for (SymbolEntry entry : symbolEntries) {
                    entry.setUsername(username);
                }
                BinaryFile.writeToFile(symbolEntries, "src/on_disk/secure.bin");
            }
        }
    }

    public void checkBoxMarked(boolean rememberUserLogin) {
        this.rememberUserLogin = rememberLoginBox.isSelected();
        System.out.println("Remember User Login: " + rememberUserLogin);
        RememberLoginHandler.saveRememberLoginState(this.rememberUserLogin);
    }

    public void openCreateFolder() {
        new NewFolder();
    }

/*    public static void main(String[] args) {
        new Dashboard("User");
    }*/
}
