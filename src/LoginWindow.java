package src;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginWindow {

    private JLabel label;
    private JButton button;
    private JFrame frame;
    private JPanel panel;

    public LoginWindow() {
        frame = new JFrame();

        label = new JLabel("Hello There");
        JButton setupButton = new JButton("Start Setup");
        JButton loginButton = new JButton("Login");
        setupButton.addActionListener(e -> setupLogin());
        loginButton.addActionListener(e -> loginUser());

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(setupButton);
        panel.add(loginButton);

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

    // Refactored method name from actionPerformed to setupLogin
    public void setupLogin() {
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
            int selectedCount = Integer.parseInt((String) Objects.requireNonNull(dropDown.getSelectedItem()));
            String username = JOptionPane.showInputDialog(frame, "Enter your username: ");

            if (username != null && !username.trim().isEmpty()) {
                showSymbolInputWindow(selectedCount, username);
            }
        }
    }

    private void showSymbolInputWindow(int count, String username) {
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
                try {
                    // Generate a SecretKey once for all encryptions in this session
                    SecretKey secretKey = Encrypt.generateSecretKey();
                    List<SymbolEntry> symbolEntries = new ArrayList<>();

                    // Process each symbol and its corresponding JTextField from our lists
                    for (int i = 0; i < inputFields.size(); i++) {
                        String symbol = symbolsList.get(i); // Get the symbol
                        String inputPhrase = inputFields.get(i).getText(); // Get the user-entered phrase

                        // Encrypt the input phrase using the encrypt method
                        String encryptedPhrase = Encrypt.encrypt(inputPhrase, secretKey);

                        symbolEntries.add(new SymbolEntry(symbol, encryptedPhrase, secretKey, username));

                        String decryptedPhrase = Encrypt.decrypt(encryptedPhrase, secretKey);

                        // Print the symbol and encrypted phrase
                        System.out.println(symbol + " : " + encryptedPhrase + " : " + decryptedPhrase);
                    }

                    BinaryFile.writeToFile(symbolEntries, "src/on_disk/secure.bin");

                    System.out.println("Reading from file: ");
                    BinaryFile.readFromFile("src/on_disk/secure.bin");

                    symbolFrame.dispose(); // Close the frame after confirmation
                } catch (Exception ex) {
                    ex.fillInStackTrace(); // Print any encryption errors
                }
            }
        });
        symbolFrame.add(new JLabel()); // Empty space for layout alignment
        symbolFrame.add(confirmButton);

        symbolFrame.setSize(600, 400);
        symbolFrame.setVisible(true);
    }

    public void loginUser() {
        try {
            // Read symbol entries from file
            List<SymbolEntry> symbolEntries = BinaryFile.readFromFile("src/on_disk/secure.bin");

            // Check if the list is empty or null
            if (symbolEntries == null || symbolEntries.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No Symbols available. Please complete setup first.");
                return;
            }

            // Get a random symbol entry from the list
            SymbolEntry entry = symbolEntries.get((int) (Math.random() * symbolEntries.size()));
            String symbol = entry.getSymbol();
            String correctEncryptedPhrase = entry.getEncryptedPhrase();
            SecretKey correctKey = entry.getKey(); // Ensure your SymbolEntry class can store the key

            // Create and display the login frame
            SwingUtilities.invokeLater(() -> {
                try {
                    JFrame loginFrame = new JFrame("Login - Enter Phrase");
                    loginFrame.setLayout(new GridLayout(2, 1));

                    JLabel symbolLabel = new JLabel(symbol, SwingConstants.CENTER);
                    JTextField phraseField = new JTextField();
                    JButton submitButton = new JButton("Submit");

                    submitButton.addActionListener(e -> {
                        try {
                            String userInput = phraseField.getText();

                            // Decrypt the phrase using the correct key
                            String decryptedPhrase = Encrypt.decrypt(correctEncryptedPhrase, correctKey);

                            if (userInput.equals(decryptedPhrase)) {
                                System.out.println("User Login Success");
                                new Dashboard();
                                loginFrame.dispose(); // Close the login window
                            } else {
                                JOptionPane.showMessageDialog(loginFrame, "Incorrect phrase. Please re-try.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });

                    loginFrame.add(symbolLabel);
                    loginFrame.add(phraseField);
                    loginFrame.add(submitButton);

                    loginFrame.setSize(600, 400);
                    loginFrame.setVisible(true);
                    loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                } catch (Exception ex) {
                    ex.printStackTrace(); // Catch any issues with window creation
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace(); // Catch any other exceptions
        }
    }

}
