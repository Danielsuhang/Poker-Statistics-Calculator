package Poker;
import java.util.concurrent.ThreadLocalRandom;

public class HandGenerator {

	public String giveCard() {
		StringBuilder result = new StringBuilder();
		int hand = ThreadLocalRandom.current().nextInt(1, 14);
		int suit = ThreadLocalRandom.current().nextInt(1, 5);

		switch (hand) {
		case 1: result.append("Ace");
		break;
		case 2: result.append("Two");
		break;
		case 3: result.append("Three");
		break;
		case 4: result.append("Four");
		break;
		case 5: result.append("Five");
		break;
		case 6: result.append("Six");
		break;
		case 7: result.append("Seven");
		break;
		case 8: result.append("Eight");
		break;
		case 9: result.append("Nine");
		break;
		case 10: result.append("Ten");
		break;
		case 11: result.append("Jack");
		break;
		case 12: result.append("Queen");
		break;
		case 13: result.append("King");
		break;
		
		}
		switch (suit) {
		case 1: result.append("Heart");
		break;
		
		case 2: result.append("Diamond");
		break;
		
		case 3: result.append("Spade");
		break;
		
		case 4: result.append("Club");
		break;
		
		}
		
		return result.toString();


	}


}
