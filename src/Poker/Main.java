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
//		Probabilities compareTwo = new Probabilities (badHand, compare, 2);
//		compareTwo.setUpOverallProb();
//		String otherProb = compareTwo.calcProb();
//		System.out.println("SameHand "  + otherProb);
		
		
	
		
		
		
		
		
		
		
		
		
		

		
		


	}

}
