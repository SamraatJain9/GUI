package src;

import javax.crypto.SecretKey;
import java.io.Serializable;

public class SymbolEntry implements Serializable {
    private String symbol;
    private String encryptedPhrase;
    private SecretKey key;
    private String username;

    public SymbolEntry(String symbol, String encryptedPhrase, SecretKey key, String username) {
        this.symbol = symbol;
        this.encryptedPhrase = encryptedPhrase;
        this.key = key;
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getEncryptedPhrase() {
        return  encryptedPhrase;
    }

    public SecretKey getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
