package src;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Encrypt {

    // Generates a new SecretKey for encryption and decryption
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // Encrypt method
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Return encrypted string
    }

    // Decrypt method
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes); // Return decrypted string
    }

    // Main method to test encryption and decryption
    public static void main(String[] args) throws Exception {
        SecretKey secretKey = generateSecretKey(); // Generate a secret key
        String plainText = "Testing";

        // Encrypt the plainText
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Encrypted text using AES: " + encryptedText);

        // Decrypt the encryptedText
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted text using AES: " + decryptedText);
    }
}
