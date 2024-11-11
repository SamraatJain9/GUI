package src;

import java.io.Serializable;

public class SymbolEntry implements Serializable {
    private String symbol;
    private String encryptedPhrase;

    public SymbolEntry(String symbol, String encryptedPhrase) {
        this.symbol = symbol;
        this.encryptedPhrase = encryptedPhrase;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getEncryptedPhrase() {
        return  encryptedPhrase;
    }
}
