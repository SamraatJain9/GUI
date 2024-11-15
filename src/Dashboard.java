package src;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JPanel settingsPanel;
    private JSplitPane splitPane;

    public Dashboard() {
        frame = new JFrame();
        frame.setTitle("Dashboard");
        label = new JLabel("Hello ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> toggleSettingsPanel());

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(settingsButton);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        settingsPanel.add(new JLabel("Username"));
        settingsPanel.add(new JLabel("Remember Login"));
        settingsPanel.setVisible(false);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, settingsPanel);
        splitPane.setDividerLocation(800);
        splitPane.setEnabled(false);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    public void toggleSettingsPanel() {
        boolean isSettingsVisible = settingsPanel.isVisible();
        settingsPanel.setVisible(!isSettingsVisible);
        splitPane.setDividerLocation(isSettingsVisible ? 1.0 : 0.8);
    }

/*    public static void main(String[] args) {
        new Dashboard("User");
    }*/
}
