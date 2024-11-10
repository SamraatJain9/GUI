package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginWindow implements ActionListener {

    private JLabel label;
    private JButton button;
    private JFrame frame;
    private JPanel panel;

    public LoginWindow() {
        frame = new JFrame();

        label = new JLabel("Hello There");
        JButton setupButton = new JButton("Start Setup");
        setupButton.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(setupButton);

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
        JPanel setupPanel = new JPanel(new GridLayout(0, 1));

        setupPanel.add(new JLabel("Setup Window"));
        JLabel choiceMessage = new JLabel("Select how many symbols do you want");
        choiceMessage.setVisible(true);
        setupPanel.add(choiceMessage);

        String[] numberOfSymbols = {"1", "2", "3", "4", "5"};
        final JComboBox<String> dropDown = new JComboBox<>(numberOfSymbols);
        dropDown.setVisible(true);
        setupPanel.add(dropDown);

        int result = JOptionPane.showConfirmDialog(frame, setupPanel, "Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int selectedCount = Integer.parseInt((String) dropDown.getSelectedItem());
            showSymbolInputWindow(selectedCount);
        }
    }

    private void showSymbolInputWindow(int count) {
        JFrame symbolFrame = new JFrame("Enter Phrases for Symbols");
        symbolFrame.setLayout(new GridLayout(count + 1, 2)); // +1 for the confirm button row
        symbolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Symbols symbolsGenerator = new Symbols();
        List<String> symbolsList = symbolsGenerator.getUniqueSymbols(count);
        List<JTextField> inputFields = new ArrayList<>(); // Store only JTextFields for later retrieval

        for (String symbol : symbolsList) {
            JLabel symbolLabel = new JLabel(symbol);
            JTextField inputField = new JTextField();
            inputFields.add(inputField); // Track the input field in our list

            symbolFrame.add(symbolLabel);
            symbolFrame.add(inputField);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process each symbol and its corresponding JTextField from our lists
                for (int i = 0; i < inputFields.size(); i++) {
                    String symbol = symbolsList.get(i); // Get the symbol
                    String inputPhrase = inputFields.get(i).getText(); // Get the user-entered phrase
                    System.out.println(symbol + " : " + inputPhrase);
                }
                symbolFrame.dispose(); // Close the frame after confirmation
            }
        });
        symbolFrame.add(new JLabel()); // Empty space for layout alignment
        symbolFrame.add(confirmButton);

        symbolFrame.setSize(600, 400);
        symbolFrame.setVisible(true);
    }
}
