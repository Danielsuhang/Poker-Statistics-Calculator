package poker;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceDiamond", "ThreeDiamond"));
		String[] board = {
				"FourDiamond", 
				"EightSpade",
				"SevenHeart",
				"TenClub",
				"KingHeart"				
		};
		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
	
//		PokerHand badHand = new PokerHand("AceHeart", "AceDiamond");
//		PokerHand compare = new PokerHand("TwoDiamond", "SevenClub");
//		
//
//		
//		game1.setPlayerHands(playerHands);
//		game1.setCurrentBoard(board);
//		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
//				, game1.getCurrentBoard()));
		
	
		PokerHand goodHand = new PokerHand("AceClub", "AceDiamond");
		PokerHand compare = new PokerHand("TwoDiamond", "SevenClub");
		

		
		Probabilities compareTwo = new Probabilities (goodHand, compare, 2);
		Probabilities compareOne = new Probabilities (goodHand, 10);
		compareTwo.setUpOverallProb();
		compareOne.setUpOverallProb();
		String oneProb = compareOne.calcProb();
		String otherProb = compareTwo.calcProb();
		System.out.println("Comparing Two Hands"  + otherProb);
		System.out.println("Chances Pocket Aces win against 9 other people: " + oneProb);
		
		
	
		
		
		
		
		
		
		
		
		
		

		
		


	}

}
