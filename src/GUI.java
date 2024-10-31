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
    private JTextField username;

    public GUI() {
        frame = new JFrame();


        username = new JTextField();
        username.setBounds(50,100, 200,30);

        label = new JLabel("What Should I Call You ");
        button = new JButton("enter");
        button.addActionListener(this);


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(username);
        panel.add(button);



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
        String user = username.getText();
        if (user.length() < 2) {
            JOptionPane.showMessageDialog(null, "Username should be more than 2 characters");
            username.setText("");
        }
        System.out.println("Username entered: " + user);
    }
}
