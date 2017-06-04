package poker;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HandGenerator {
	private static List<String> NAME_LIST = Arrays
			.asList("Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King");
	private static List<String> SUIT_LIST = Arrays.asList("Heart", "Diamond", "Spade", "Club");  
	private static PokerHand[] goodrange; 

	public String giveCard() {
		final int hand = ThreadLocalRandom.current().nextInt(0, 13);
		final int suit = ThreadLocalRandom.current().nextInt(0, 4);

		return NAME_LIST.get(hand) + SUIT_LIST.get(suit);
	}
	public void setGoodHandRange (PokerHand[] goodrange) {
		HandGenerator.goodrange = goodrange;
	}
	//Returns a pair of cards (PokerHand) rather than individually
	public PokerHand getGoodHand() {
		if (goodrange == null) {
			System.err.println("Error, goodrange has not been set");
			return null;
		}
		else {
			PokerHand genHand = goodrange[ThreadLocalRandom.current().nextInt(0, goodrange.length)];
			if (genHand.getCard1().contains("Diamond") || genHand.getCard1().contains("Club") 
					|| genHand.getCard1().contains("Heart") || genHand.getCard1().contains("Spade")) {
				System.err.println("When defining goodrange, DO NOT INCLUDE A SUIT, ONLY INCLUDE"
						+ "'SUITED' or don't mention anything after numbers. You added a suit to card1 ");   //Error checking
				if (genHand.getCard2().contains("Diamond") || genHand.getCard2().contains("Club") 
					|| genHand.getCard2().contains("Heart") || genHand.getCard2().contains("Spade")) {
					System.err.println("When defining goodrange, DO NOT INCLUDE A SUIT, ONLY INCLUDE"
							+ "'SUITED' or don't mention anything after numbers. You added a suit to card2 ");
					return null;
				}
				else {
					return null;
				}
			}
			
			else {
				if (genHand.getSuited()) {
					final int suit = ThreadLocalRandom.current().nextInt(0, 4);
					String suited = SUIT_LIST.get(suit);
					return new PokerHand (genHand.getCard1() + suited
							, genHand.getCard2() + suited);
				}
				else {
					int suit1;
					int suit2;
					do {
						suit1 = ThreadLocalRandom.current().nextInt(0, 4);
						suit2 = ThreadLocalRandom.current().nextInt(0, 4);
					}
					while (suit1 == suit2);
					return new PokerHand (genHand.getCard1() + SUIT_LIST.get(suit1),
							genHand.getCard2() + SUIT_LIST.get(suit2));
					
					
				}
			}
		}


	}

}
