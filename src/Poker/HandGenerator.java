package poker;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HandGenerator {
    private static List<String> NAME_LIST = Arrays
        .asList("Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King");
    private static List<String> SUIT_LIST = Arrays.asList("Heart", "Diamond", "Spade", "Club");

    public String giveCard() {
        final int hand = ThreadLocalRandom.current().nextInt(0, 13);
        final int suit = ThreadLocalRandom.current().nextInt(0, 4);

        return NAME_LIST.get(hand) + SUIT_LIST.get(suit);
    }
}
