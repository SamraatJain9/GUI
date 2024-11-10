package src;

import java.io.*;

public class BinaryFile {

    public static void writeToFile(String data, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(data);
            System.out.println("Data written to file: " + data);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void readFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            String data = (String) ois.readObject();
            System.out.println("Data read from file: " + data);
        } catch (IOException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename = "src/on_disk/secure.bin";

        writeToFile("Testing again", filename);

        readFromFile(filename);

    }
}
