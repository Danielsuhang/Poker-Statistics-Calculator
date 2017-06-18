package poker;
import java.util.Scanner;

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





		Scanner input = new Scanner(System.in);
		System.out.println("When defining a card, please define the Numerical value and suit");
		System.out.println("For example: For Ace, just type 'Ace', please capitalize the suit and numerical value ");
		System.out.println("FOR NOW, DO NOT ADD SPACES: Write like this: AceHeart, AceDiamond, etc etc");
		System.out.println("Type the first Card for your initial hand: ");
		String hand1 = input.nextLine();
		System.out.println("Type the second Card for your initial hand: ");
		String hand2 = input.nextLine();
		PokerHand goodHand = new PokerHand(hand1, hand2);
		System.out.println("How many players are in this game? ");
		int numPlayers = input.nextInt();

		System.out.println();
		System.out.println("Would you like to set a range for possible cards your opponent will get?");
		System.out.println("If you want to use my default range which includes: ");
		System.out.println("Pocket Aces to Pocket Sevens, Ace King, Ace Queen, Ace Jack, Ace Ten both suited and unsuited");
		System.out.println("King Queen suited/unsuited, King Jack suited/unsuited, Queen Jack suited/unsuited");

		PokerHand[] goodrange = {new PokerHand("Ace", "Ace"), new PokerHand("King", "King")
				, new PokerHand ("Ace", "King", true), new PokerHand ("Ace", "King", false)
				, new PokerHand ("Queen", "Queen"), new PokerHand("Ace", "Queen", true)
				, new PokerHand ("Ace", "Queen", false), new PokerHand("Jack", "Jack"), 
				new PokerHand ("Ten", "Ten"), new PokerHand("Ace", "Jack", true)
				, new PokerHand("Ace", "Jack", false), new PokerHand("King", "Jack", true)
				, new PokerHand("King", "Jack", false), new PokerHand("Queen", "Jack", true),
				new PokerHand("Queen", "Jack", false), new PokerHand("Nine", "Nine"),
				new PokerHand("Eight", "Eight"), new PokerHand("Seven", "Seven") , new PokerHand("Ace ", "Ten", true)
				, new PokerHand("Ace ", "Ten", false)};
		System.out.println("Type yes or no to include range: ");
		String answer = input.nextLine();
		System.out.println();

		input.nextLine();
		String runAgain;

		do {
			if (answer.toUpperCase() == "YES") {
				Probabilities prob = new Probabilities(goodHand, numPlayers, goodrange);
				System.out.println(prob.calculatePercentage(true));
			}
			else {
				Probabilities prob = new Probabilities(goodHand, numPlayers);
				System.out.println(prob.calculatePercentage(false));
			}
			System.out.println("Would you like to run the same program again? Type yes");
			runAgain = input.nextLine();
		}
		while(runAgain.toUpperCase().equals("YES"));

		

		//		
		//		
		//		//Other one was AceClub and AceDiamond
		//		Probabilities compareOne = new Probabilities (goodHand, 2);
		//		compareOne.game.setGoodPokerHand(goodrange);
		//		System.out.println(compareOne.calculatePercentage(true));


		//		for (int i = 2; i <= 10; i++) {
		//			Probabilities compareOne = new Probabilities (goodHand, i);
		//			compareOne.game.setGoodPokerHand(goodrange);
		//			System.out.println("Chances Ace King suited win against " + (i - 1) +  " other person: " + compareOne.calculatePercentage(false));
		//		}





















	}

}
