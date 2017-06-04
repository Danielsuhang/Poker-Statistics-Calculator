package poker;

//import java.util.HashMap;
//import java.util.Map;

public class Main {

	public static void main(String[] args) {
		//This program gives you the option of finding out a win percentage against a specific hand (or hands)
		//    for example: (Ace Ace v. Two Seven)
		
		//It also allows you to find the percentage chance of winning with a specific hand against a number of opponents
		//The opponent's hands are generated RANDOMLY. 
		//Note: The program will NOT give already used cards to any of your opponents nor will it use them as cards on the board
		
		//To calculate a percentage of a specific hand against a specific hand, create two PokerHand instances, and 
		//give them a card value and suit. Please use upper case for the card type and suit i.e: AceHeart or TwoClub
		
		//Then create an instance of Probabilities and first put in hand you want percentage win for, then hand you're comparing
		//with, then put in the number of players in the hand. I.e: Probabilities name = new Probabilities (mainhand, comparehand)
		// If you want to add in additional random hands in, then include the num of players as the third parameter 
		
		//If you want to compare ONE specific hand with several random hands, then simply put in the PokerHand instance as the 
		//first parameter and then the number of people (INCLUDING YOURSELF) in the hand.
		
		//Note: This program is assuming no one folds at all until you reach the turn. This will give you a good idea on how good the hand
		//strength is until the end
		
		//Features in-progress of being added in:
		//Calculating percentage of winning JUST ON THE FLOP, Supporting comparing one main hand with more than one other specific hand
		//Supporting hand ranges
		

		
	
		PokerHand goodHand = new PokerHand("AceClub", "AceHeart");
		PokerHand[] goodrange = {new PokerHand("Ace", "Ace"), new PokerHand("King", "King")};
		
		
		//Other one was AceClub and AceDiamond
		Probabilities compareOne = new Probabilities (goodHand, 2);
		compareOne.game.setGoodPokerHand(goodrange);
		System.out.println(compareOne.calculatePercentage(true));
		

//		for (int i = 2; i <= 10; i++) {
//			Probabilities compareOne = new Probabilities (goodHand, i);
//			compareOne.game.setGoodPokerHand(goodrange);
//			System.out.println("Chances Ace King suited win against " + (i - 1) +  " other person: " + compareOne.calculatePercentage(false));
//		}
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		

		
		


	}

}
