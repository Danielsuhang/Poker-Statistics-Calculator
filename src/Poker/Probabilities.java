package poker;

import static poker.utils.PokerUtils.checkFullHouse;
import static poker.utils.PokerUtils.convertHighCards;
import static poker.utils.PokerUtils.convertInt;
import static poker.utils.PokerUtils.convertStrAdvanced;
import static poker.utils.PokerUtils.numericalHandStrength;

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
		for (int i = 0; i < (numPlayers * 5000); i++) {
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
			if (convertInt(mainStrength) > convertInt(competeStrength)) {
				return 1;
			}
			else if (convertInt(mainStrength) < convertInt(competeStrength)){
				return -1;
			}
			else {
				//This block is run if the "same" strength hand is acheived. We need to look at high card if this occurs
				if (mainStrength.contains("Straight") || mainStrength.contains("Four of a Kind")) {  //For both straight flush and normal straight
					int[] mainHigh = convertHighCards(mainStrength); //Only gives us high card
					int[] competeHigh = convertHighCards(competeStrength);
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
					int[] mainHigh = checkFullHouse(mainStrength);
					int[] competeHigh = checkFullHouse(competeStrength);
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
					int mainMaxThree = convertStrAdvanced(mainStrength);
					int competeMaxThree = convertStrAdvanced(competeStrength);
					int[] mainHigh = convertHighCards(mainStrength);  
					int[] competeHigh = convertHighCards(competeStrength);
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
					int[] mainHigh = convertHighCards(mainStrength);  
					int[] competeHigh = convertHighCards(competeStrength);
					int flushArrayChecker = 0;
					while (flushArrayChecker <= 4) {
						if (mainHigh[flushArrayChecker] > competeHigh[flushArrayChecker]) {
							return 1;
						}
						else if (mainHigh[flushArrayChecker] < competeHigh[flushArrayChecker]) {
							return -1;
						}
						else {
							flushArrayChecker++;
						}
					}
					return 0;
				}
				else if (mainStrength.contains("Two Pair")) {
					int[] mainHigh = checkFullHouse(mainStrength);  
					int[] competeHigh = checkFullHouse(competeStrength);
					String newMainStr = mainStrength.substring(24, 35);
					String newCompeteStr = competeStrength.substring(23, 35);
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
							if (convertInt(newMainStr) > convertInt(newCompeteStr)) {
								return 1;
							}
							else if (convertInt(newMainStr) < convertInt(newCompeteStr)) {
								return -1;
							}
							else {
								return 0;
							}
						}
					}


				}
				else if (mainStrength.contains("One Pair")) {
					int mainPair = convertInt(mainStrength);
					int competePair = convertInt(competeStrength);
					int[] mainHigh = convertHighCards(mainStrength);  
					int[] competeHigh = convertHighCards(competeStrength);
					if (mainPair > competePair) {
						return 1;
					}
					else if (mainPair < competePair) {
						return -1;
					}
					else {
						int onePairArrayChecker = 0;
						while (onePairArrayChecker <= 3) {
							if (mainHigh[onePairArrayChecker] > competeHigh[onePairArrayChecker]) {
								return 1;
							}
							else if (mainHigh[onePairArrayChecker] < competeHigh[onePairArrayChecker]) {
								return -1;
							}
							else {
								onePairArrayChecker++;
							}
						}
						return 0;
					}
				}
				else {  //No hand
					int[] mainHigh = convertHighCards(mainStrength);  
					int[] competeHigh = convertHighCards(competeStrength);
					int noPairArrayChecker = 6;
					while (noPairArrayChecker >= 0) {
						if (mainHigh[noPairArrayChecker] > competeHigh[noPairArrayChecker]) {
							return 1;
						}
						else if (mainHigh[noPairArrayChecker] < competeHigh[noPairArrayChecker]) {
							return -1;
						}
						else {
							noPairArrayChecker--;
						}
					}
					return 0;
				}

			}
		}
	}
}
