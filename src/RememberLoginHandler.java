package src;

import java.io.*;

public class RememberLoginHandler {
    private static final String FILE_PATH = "src/on_disk/remember_login.bin";

    public static void saveRememberLoginState(boolean state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeBoolean(state);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static boolean loadRememberLoginState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return in.readBoolean();
        } catch (IOException e) {
            e.fillInStackTrace();
            return false;
        }
    }
}
