package src;

import java.util.*;

public class Symbols {
    Random random = new Random();
    private String[] symbols = {
            "||__||",
            "/\\_/\\",
            ">==(*)==<",
            "--(o)--",
            "~\\~/\\~"
    };


    public List<String> getUniqueSymbols(int count) {
        List<String> symbolsList = new ArrayList<>(List.of(symbols));
        Collections.shuffle(symbolsList);
        return symbolsList.subList(0, Math.min(count, symbolsList.size()));
    }
}
