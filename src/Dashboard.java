package src;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public Dashboard(String username) {
        frame = new JFrame();

        label = new JLabel("Hello " + username);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dashboard");
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

/*    public static void main(String[] args) {
        new Dashboard("User");
    }*/
}
