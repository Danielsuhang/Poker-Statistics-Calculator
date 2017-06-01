package poker;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@org.junit.Test
	public void testFlushWithStraight() {
		Game game2 = new Game();
		game2.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceHeart", "KingClub"));
		String[] board = {
				"QueenDiamond", 
				"JackDiamond",
				"TenDiamond",
				"NineDiamond",
				"SevenDiamond"				
		};
		game2.setCurrentBoard(board);
		game2.setPlayerHands(playerHands);
		
		
		game2.calcHandStrength(game2.getMapPlayerHands().get(1)
				, game2.getCurrentBoard());
		System.out.println(game2.calcHandStrength(game2.getMapPlayerHands().get(1)
				, game2.getCurrentBoard()));
		assertEquals("Flush -12- -11- -10- -9- -7-", game2.calcHandStrength(game2.getMapPlayerHands().get(1)
				, game2.getCurrentBoard()));
		
	}

	@org.junit.Test
	public void testStraightFlush() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("Ace Diamond", "KingClub"));
		String[] board = {
				"QueenDiamond", 
				"JackDiamond",
				"TenDiamond",
				"NineDiamond",
				"EightDiamond"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("StraightFlush -12- high", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	
	@org.junit.Test
	public void testRoyalFlush() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("Ace Diamond", "KingDiamond"));
		String[] board = {
				"QueenDiamond", 
				"JackDiamond",
				"TenDiamond",
				"NineDiamond",
				"EightDiamond"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("ROYALFLUSH", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testLongStraight() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "KingDiamond"));
		String[] board = {
				"QueenSpade", 
				"JackDiamond",
				"TenDiamond",
				"Nineheart",
				"EightDiamond"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Straight -14- high", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testAceFiveStraight() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "TwoDiamond"));
		String[] board = {
				"ThreeSpade", 
				"FourDiamond",
				"KingDiamond",
				"Fiveheart",
				"QueenDiamond"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Straight -5- high", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testAceFiveFlush() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "TwoDiamond"));
		String[] board = {
				"ThreeClub", 
				"FourDiamond",
				"KingClub",
				"FiveClub",
				"QueenClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Flush -14- -13- -12- -5- -3-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testStraightDuplicates() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"ThreeClub", 
				"FourDiamond",
				"KingClub",
				"TwoClub",
				"QueenClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Flush -14- -13- -12- -3- -2-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testNoHand() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("FiveClub", "NineDiamond"));
		String[] board = {
				"ThreeClub", 
				"FourDiamond",
				"KingClub",
				"TwoClub",
				"QueenHeart"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("High Card Kings -4- -5- -9- -12- -13-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testFourKindAce() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"ThreeClub", 
				"FourDiamond",
				"KingClub",
				"AceHeart",
				"AceClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Four of a Kind -14-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testFourKindOther() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("FiveClub", "FiveDiamond"));
		String[] board = {
				"ThreeClub", 
				"KingDiamond",
				"KingClub",
				"FiveHeart",
				"FiveClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Four of a Kind -5-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testFullHouse() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"FiveDiamond", 
				"KingDiamond",
				"KingClub",
				"FiveHeart",
				"FiveClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("FullHouse --5-- over -14-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testThreeKind() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"FiveDiamond", 
				"KingDiamond",
				"QueenClub",
				"AceHeart",
				"TwoClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Three of a Kind Aces: -13- -12- -14- -14- -14-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testTwoPair() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"FiveDiamond", 
				"KingDiamond",
				"FiveClub",
				"KingHeart",
				"TwoClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("Two Pair --14-- and -13-        5      ", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testPair() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"FiveDiamond", 
				"KingDiamond",
				"FourClub",
				"QueenHeart",
				"TwoClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertEquals("One Pair Aces -13- -12- -5- -14- -14-", game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));

	}
	@org.junit.Test
	public void testAlmostFlush() {
		Game game1 = new Game();
		game1.setPlayers(1);
		Map<Integer, PokerHand> playerHands = new HashMap<>();
		playerHands.put(1, new PokerHand("AceClub", "AceDiamond"));
		String[] board = {
				"FiveClub", 
				"KingDiamond",
				"FourClub",
				"QueenHeart",
				"TwoClub"				
		};

		
		game1.setPlayerHands(playerHands);
		game1.setCurrentBoard(board);
		System.out.println(game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()));
		
		assertFalse((game1.calcHandStrength(game1.getMapPlayerHands().get(1)
				, game1.getCurrentBoard()).contains("Flush")));

	}
	@org.junit.Test
	public void testHandStrengthTwoFourKind() {
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("KingClub", "KingHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceSpade", "AceDiamond", "KingSpade", "KingDiamond", "TwoDiamond"};

		
		prob.setUpOverallProb();
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void testHandStrengthStraightFlushExtend() {
		PokerHand hand1 = new PokerHand("AceClub", "KingClub");
		PokerHand hand2 = new PokerHand("SevenClub", "SixClub");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"QueenClub", "JackClub", "TenClub", "NineClub", "EightClub"};

		prob.game.setCurrentBoard(board);
		System.out.println(prob.calcWinner(hand2, hand1, board));
		assertEquals(1, prob.calcWinner(hand1, hand2, board));

	}
	@org.junit.Test
	public void testHandStrengthStraightTie() {
		PokerHand hand1 = new PokerHand("AceClub", "TwoClub");
		PokerHand hand2 = new PokerHand("FiveClub", "SixClub");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"QueenClub", "JackClub", "TenClub", "NineClub", "EightClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()));
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()));
		assertEquals(0, prob.calcWinner(hand2, hand1, board));
	}
	@org.junit.Test
	public void testHandFlushHigh() {
		PokerHand hand1 = new PokerHand("AceClub", "TwoClub");
		PokerHand hand2 = new PokerHand("KingClub", "QueenClub");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"ThreeClub", "FourClub", "FiveClub", "NineClub", "EightClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "LOOKHERE");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "LOOKHERE");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));

	}
	@org.junit.Test
	public void testHandFullHouse() {
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("KingClub", "QueenClub");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"ThreeClub", "ThreeSpade", "ThreeHeart", "NineClub", "KingClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "LOOKHERE");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "LOOKHERE");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));

	}
	@org.junit.Test
	public void testHandFourKind() {
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("KingClub", "KingHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"ThreeClub", "AceSpade", "ThreeHeart", "AceDiamond", "KingClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "LOOKHERE");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "LOOKHERE");
		assertEquals(-1, prob.calcWinner(hand2, hand1, board));
	}
	@org.junit.Test
	public void testHandRoyalFlush() {
		PokerHand hand1 = new PokerHand("AceClub", "KingClub");
		PokerHand hand2 = new PokerHand("NineClub", "EightClub");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"QueenClub", "JackClub", "TenClub", "AceDiamond", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "LOOKHERE");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "LOOKHERE");
		assertEquals(-1, prob.calcWinner(hand2, hand1, board));
	}
	@org.junit.Test
	public void testHandTwoPair() {
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("NineClub", "NineHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"QueenClub", "QueenHeart", "TenClub", "TwoDiamond", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "LOOKHERE");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "LOOKHERE");
		assertEquals(-1, prob.calcWinner(hand2, hand1, board));
	}
	@org.junit.Test
	public void testHandTwoPair2() {
		PokerHand hand1 = new PokerHand("AceClub", "TenHeart");
		PokerHand hand2 = new PokerHand("KingClub", "QueenHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "QueenClub", "KingHeart", "TwoDiamond", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "TwoPair");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "TwoPair");
		assertEquals(-1, prob.calcWinner(hand2, hand1, board));
	}
	@org.junit.Test
	public void testHandTwoPairHighCard() {
		PokerHand hand1 = new PokerHand("TenClub", "NineHeart");
		PokerHand hand2 = new PokerHand("NineClub", "EightHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "AceHeart", "KingHeart", "KingClub", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "TwoPairHigh");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "TwoPairHigh");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void testHandThreePairHighCard() {
		PokerHand hand1 = new PokerHand("TenClub", "TenHeart");
		PokerHand hand2 = new PokerHand("JackClub", "EightHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "AceHeart", "KingHeart", "KingClub", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "TwoPairHigh");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "TwoPairHigh");
		assertEquals(-1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void testHandPairHighCard() {
		PokerHand hand1 = new PokerHand("TenClub", "NineHeart");
		PokerHand hand2 = new PokerHand("JackClub", "EightHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "AceHeart", "KingHeart", "QueenClub", "TwoClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "new");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "new");
		assertEquals(-1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void testHighCard() {
		PokerHand hand1 = new PokerHand("FiveClub", "SixHeart");
		PokerHand hand2 = new PokerHand("ThreeClub", "FourHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "JackHeart", "KingHeart", "QueenClub", "NineClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(0, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void testHighCard2() {
		PokerHand hand1 = new PokerHand("TenHeart", "SixHeart");
		PokerHand hand2 = new PokerHand("ThreeClub", "FourHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "JackHeart", "KingHeart", "QueenClub", "NineClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void TestStraightFlushContingency() {
		PokerHand hand1 = new PokerHand("NineClub", "SixHeart");
		PokerHand hand2 = new PokerHand("ThreeClub", "FourHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceClub", "KingClub", "JackClub", "QueenClub", "TenClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(0, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void BasicStrengthTests1() {
		PokerHand hand1 = new PokerHand("AceClub", "SixHeart");
		PokerHand hand2 = new PokerHand("NineClub", "FourHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"EightClub", "KingClub", "JackClub", "QueenClub", "TenClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void BasicStrengthTests2() {
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("JackClub", "JackHeart");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceDiamond", "JackDiamond", "QueenHeart", "QueenClub", "TenClub"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}
	@org.junit.Test
	public void BasicStrengthTests3() {  //FullHouse v Flush
		PokerHand hand1 = new PokerHand("AceClub", "AceHeart");
		PokerHand hand2 = new PokerHand("TwoDiamond", "ThreeDiamond");
		Probabilities prob = new Probabilities(hand1, hand2, 2);
		String[] board = {"AceDiamond", "JackDiamond", "QueenHeart", "QueenClub", "TenDiamond"};
		prob.game.setCurrentBoard(board);
		//prob.setUpOverallProb();   Because it regenerates the board, it will cause problems 
		
		System.out.println(prob.game.calcHandStrength(hand1, prob.game.getCurrentBoard()) + "   HighCard");
		System.out.println(prob.game.calcHandStrength(hand2, prob.game.getCurrentBoard()) + "   HighCard");
		assertEquals(1, prob.calcWinner(hand1, hand2, board));
	}


	


}
