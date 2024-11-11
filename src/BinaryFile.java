package src;

import java.io.*;
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

    public static void readFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            List<SymbolEntry> symbolEntries = (List<SymbolEntry>) ois.readObject();

            for (SymbolEntry entry : symbolEntries) {
                System.out.println("Symbol: " + entry.getSymbol() + " Phrase(encrypted): " + entry.getEncryptedPhrase());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
    }

//    public static void main(String[] args) {
//        String filename = "src/on_disk/secure.bin";
//
//        writeToFile("Testing again", filename);
//
//        readFromFile(filename);
//
//    }
}
