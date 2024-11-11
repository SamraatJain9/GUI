package src;

import javax.crypto.SecretKey;
import java.io.Serializable;

public class SymbolEntry implements Serializable {
    private String symbol;
    private String encryptedPhrase;
    private SecretKey key;

    public SymbolEntry(String symbol, String encryptedPhrase, SecretKey key) {
        this.symbol = symbol;
        this.encryptedPhrase = encryptedPhrase;
        this.key = key;
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
}
