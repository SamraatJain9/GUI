package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    int counts = 0;

    private JLabel label;
    private JButton button;
    private JFrame frame;
    private JPanel panel;

    public GUI() {
        frame = new JFrame();

        button = new JButton("Click");
        button.addActionListener(this);
        label = new JLabel("Number of Clicks:");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    public void actionPerformed(ActionEvent e) {
        counts++;
        label.setText("Number of clicks: " + counts);
    }
}
