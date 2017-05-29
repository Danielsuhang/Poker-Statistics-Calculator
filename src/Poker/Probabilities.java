package Poker;

import java.util.HashMap;
import java.util.Map;

public class Probabilities {
	//This class is designed to run hundreds of thousands of simulations of poker games with specific hands, running 
	//To calculate probabilities of winning with certain hands. 
	//Constructor will take in a poker hand
	PokerHand mainHand;
	PokerHand compare;
	int numPlayers = 0;
	Game game;
	double wins = 0;
	double tie = 0;
	double total = 0;


	public Probabilities(PokerHand mainHand, int numPlayers) {
		this.mainHand = mainHand;    //Constructor adds initial hand but doesn't change board state
		this.numPlayers = numPlayers;
		game = new Game (numPlayers);
		Map<Integer, PokerHand> initialize = new HashMap<>();
		initialize.put(0, mainHand);
		game.setPlayerHands(initialize);   
	}
	public Probabilities (PokerHand mainHand, PokerHand compare, int numPlayers) {
		if (!(numPlayers < 2)) {
			this.mainHand = mainHand;    //Constructor adds two hands to initialized hands, won't change with simulations
			this.compare = compare;
			this.numPlayers = numPlayers;
			game = new Game (numPlayers);
			Map<Integer, PokerHand> initialize = new HashMap<>();
			initialize.put(0, mainHand);
			initialize.put(1, compare);

			game.setPlayerHands(initialize);   
		}
	}
	public void setDefinedHands (PokerHand other) {
		game.addPlayerHands(other);

	}
	public void setUpOverallProb() {   
		//Add a loop here and do this many many times
		for (int i = 0; i < 10; i++) {
			boolean tieBool = false; 
			boolean win = true;
			game.giveInitial();  //This part of the method keeps giving opponents randomhands
			game.flop();
			game.turn();
			game.river();

			for (Map.Entry<Integer, PokerHand> entry : game.getPokerHands().entrySet()) {
				if (!(entry.getKey() == 0)) {
					if (calcWinner(mainHand, entry.getValue(), game.getCurrentBoard()) == -1) {
						win = false;
						break;
					}
					else if (calcWinner(mainHand, entry.getValue(), game.getCurrentBoard()) == 0) {
						tieBool = true;
					}
				}
			}
			total++;   //Total always increases
			if (win && tieBool) {
				tie++;
			}
			else if (win) {
				wins++;
			}
			else {
				//If both tieBool and win are false, then we lost
			}
			game.newGame(numPlayers); //Resets the game, but you need to 

			if (compare != null) {
				Map<Integer, PokerHand> initialize = new HashMap<>();
				initialize.put(0, mainHand);
				game.setPlayerHands(initialize);   
				setDefinedHands(compare);
			}
			else {
				Map<Integer, PokerHand> initialize = new HashMap<>();
				initialize.put(0, mainHand);
				game.setPlayerHands(initialize);  
			}
		}
	}
	public String calcProb() {
		double winProb = wins / total;
		double tieProb = tie / total;
		return "Win Probability: " + winProb + " Tie Probability: " + tieProb;
	}
	public int calcWinner (PokerHand main, PokerHand compete, String[] board) {
		//if main wins, return 1 
		//if compete wins, return -1
		//if tie, return 0
		String mainStrength = game.calcHandStrength(main, board);
		String competeStrength = game.calcHandStrength(compete, board);
		int mainNumStr = numericalHandStrength(mainStrength);
		int competeNumStr = numericalHandStrength(competeStrength);

		if ((Integer.compare(mainNumStr, competeNumStr)) > 0) {
			return 1;
		}
		else if ((Integer.compare(mainNumStr, competeNumStr)) < 0) {
			return -1;
		}
		else {
			if (game.convertInt(mainStrength) > game.convertInt(competeStrength)) {
				return 1;
			}
			else if (game.convertInt(mainStrength) < game.convertInt(competeStrength)){
				return -1;
			}
			else {
				//This block is run if the "same" strength hand is acheived. We need to look at high card if this occurs
				if (mainStrength.contains("Straight") || mainStrength.contains("Four of a Kind")) {  //For both straight flush and normal straight
					int[] mainHigh = game.convertHighCards(mainStrength); //Only gives us high card
					int[] competeHigh = game.convertHighCards(competeStrength);
					if (mainHigh[0] > competeHigh[0]) {
						return 1;
					}
					else if (mainHigh[0] < competeHigh[0]) {
						return -1;
					}
					else {
						return 0;  //Complete tie
					}
				}
				else if (mainStrength.contains("FullHouse")) {
					int[] mainHigh = game.checkFullHouse(mainStrength);
					int[] competeHigh = game.checkFullHouse(competeStrength);
					if (mainHigh[0] > competeHigh[0]) {
						return 1;
					}
					else if (mainHigh[0] < competeHigh[0]) {
						return -1;
					}
					else {
						if (mainHigh[1] > competeHigh[1]) {
							return 1;
						}
						else if (mainHigh[1] < competeHigh[1]) {
							return -1;   //Three of a kind same, but pair is different
						}
						else {
							return 0; //Complete Tie
						}
					}
				}
				else if (mainStrength.contains("Three of a Kind")) { //For three of a kind, two pair, one pair, high card
					int mainMaxThree = game.convertStrAdvanced(mainStrength);
					int competeMaxThree = game.convertStrAdvanced(competeStrength);
					int[] mainHigh = game.convertHighCards(mainStrength);  
					int[] competeHigh = game.convertHighCards(competeStrength);
					if (mainMaxThree > competeMaxThree) {  //Checking which triple is higher
						return 1;
					}
					else if (mainMaxThree < competeMaxThree) {
						return -1;
					}
					else {  //If both the triples ARE THE SAME   NOTE: Array in descending order, you should only check none trip numbers
						//The trips are the in back three numbers of the array, so we only need to check nums at 0 and 1 index
						if (mainHigh[0] > competeHigh[0]) {
							return 1;
						}
						else if (mainHigh[0] < competeHigh[0]) {
							return -1;
						}
						else {
							if (mainHigh[1] > competeHigh[1]) {
								return 1;
							}
							else if (mainHigh[1] < competeHigh[1]) {
								return -1;
							}
							else {
								return 0;
							}
						}
					}
				}
				else if (mainStrength.contains("Flush")) {   
					int[] mainHigh = game.convertHighCards(mainStrength);  
					int[] competeHigh = game.convertHighCards(competeStrength);
				}

			}
		}
	}


	public int numericalHandStrength (String handLevel) {
		if (handLevel.contains("High Card")) return 1;
		if (handLevel.contains("One Pair")) return 2;
		if (handLevel.contains("Two Pair")) return 3;
		if (handLevel.contains("Three of a Kind")) return 4;
		if (handLevel.contains("Straight")) return 5;
		if (handLevel.contains("Flush")) return 6;
		if (handLevel.contains("FullHouse")) return 7;
		if (handLevel.contains("Four of a Kind")) return 8;
		if (handLevel.contains("StraightFlush")) return 9;
		if (handLevel.contains("ROYALFLUSH")) return 10;

		return -1;
	}



}
