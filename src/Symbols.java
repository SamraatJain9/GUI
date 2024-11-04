package src;

import java.util.Random;

public class Symbols {
    Random random = new Random();
    private String[] symbols = {
            "||__||",
            "/\\_/\\",
            ">==(*)==<",
            "--(o)--",
            "~\\~/\\~"
    };

    private String[] phrases = {
            "tracks in the snow",
            "mountain shadows",
            "wings in the wind",
            "ring of fire",
            "waves on the shore"
    };

    public String getRandomSymbol() {
        int symbol_id = random.nextInt(symbols.length);
        return symbols[symbol_id];
    }
}
