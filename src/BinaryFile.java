package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile {

    public static void writeToFile(List<SymbolEntry> symbolEntries, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(symbolEntries);
            System.out.println("Data written to file: " + symbolEntries);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static List<SymbolEntry> readFromFile(String filename) {
        List<SymbolEntry> symbolEntries = new ArrayList<>(); // Initialize an empty list
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            symbolEntries = (List<SymbolEntry>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.fillInStackTrace(); // You can log it to console for debugging purposes
        }
        return symbolEntries;
    }
}
