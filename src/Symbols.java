package src;

import java.util.Random;

public class Symbols {
    public static void main(String[] args) {

        Random random = new Random();

        int min = 1;
        int max = 5;


        //System.out.println("Generating number between " + min + " and " + max);

        int symbol_id = random.nextInt(max-min+1)+min;

        switch (symbol_id){
            case 1:
                System.out.println("||__||");
                System.out.println("tracks in the snow");
                break;
            case 2:
                System.out.println("/\\_/\\");
                System.out.println("mountain shadows");
                break;
            case 3:
                System.out.println(">==(*)==<");
                System.out.println("wings in the wind");
                break;
            case 4:
                System.out.println("--(o)--");
                System.out.println("ring of fire");
                break;
            case 5:
                System.out.println("~\\~/\\~");
                System.out.println("waves on the shore");
                break;
        }
    }
}
