package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow implements ActionListener {

    private JLabel label;
    private JButton button;
    private JFrame frame;
    private JPanel panel;
    private JTextField username;

    public LoginWindow() {
        frame = new JFrame();

        username = new JTextField();
        username.setBounds(50,100, 200,30);

        label = new JLabel("What Should I Call You ");
        button = new JButton("login");
        button.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(username);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login Window");
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new LoginWindow();
    }

    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        boolean isValid = user.matches("^\\S{3,15}$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Username should be more than 3-15 characters with no whitespaces");
            username.setText("");
        } else {
            frame.dispose();
            Dashboard dashboard = new Dashboard(user);
        }
        System.out.println("Username entered: " + user);
    }
}
