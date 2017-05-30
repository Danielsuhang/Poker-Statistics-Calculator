package poker;

import static poker.utils.PokerUtils.convertInt;
import static poker.utils.PokerUtils.convertStr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<String> usedHands = new ArrayList<>();
    private Map<Integer, PokerHand> playerHands = new HashMap<>();
    private String[] currentBoard = new String[5];
    int numPlayers;
    int counter = 0;
    public int numOfCardsBoard = 0;


    public Game(int players) {
        numPlayers = players;
    }

    public Game() {
        numPlayers = 0;
    }

    public void setPlayers(int players) {
        numPlayers = players;
    }

    public void giveInitial() {

        while (counter < numPlayers) {
            playerHands.put(counter++, generatePair());
        }
    }

    public void flop() {

        currentBoard[0] = generateHand();
        currentBoard[1] = generateHand();
        currentBoard[2] = generateHand();
        numOfCardsBoard = 3;
    }

    public void turn() {
        currentBoard[3] = generateHand();
        numOfCardsBoard = 4;
    }

    public void river() {
        currentBoard[4] = generateHand();
        numOfCardsBoard = 5;
    }

    public List<String> getUsedHands() {
        return usedHands;
    }

    public Map<Integer, PokerHand> getPokerHands() {
        return playerHands;
    }

    public void addPlayerHands(PokerHand additionalPlayer) {
        if (checkCardAvaliable(additionalPlayer)) {
            if (usedHands.size() < 52) {
                playerHands.put(counter, additionalPlayer);
                usedHands.add(additionalPlayer.getCard1());
                usedHands.add(additionalPlayer.getCard2());
                counter++;
            } else {
                System.out.println("ERROR: Not enough cards left to make more hands");
            }
        } else {
            System.out.println("These cards has been used already, cannot be dealt again");
        }

    }

    public PokerHand generatePair() {  //Process prevents repeats
        HandGenerator gen = new HandGenerator();
        while (usedHands.size() < 53) {  //Gives all players hands
            String tempHand1 = gen.giveCard();
            if (!usedHands.contains(tempHand1)) {
                usedHands.add(tempHand1);   //Won't run unless first card works

                while (usedHands.size() < 53) { //Won't continue unless second card works
                    String tempHand2 = gen.giveCard();
                    if (!usedHands.contains(tempHand2)) {    //Won't work unless second card works
                        usedHands.add(tempHand2);
                        //If both conditions are met, then finally move on to next player
                        PokerHand play = new PokerHand(tempHand1, tempHand2);
                        return play;

                    }
                }
            }
        }
        return null;
    }

    public String generateHand() {
        HandGenerator gen = new HandGenerator();
        while (usedHands.size() < 53) {
            String tempHand = gen.giveCard();
            if (!usedHands.contains(tempHand)) {
                usedHands.add(tempHand);
                return tempHand;
            }

        }
        return null;
    }

    public String[] getStringPlayerHands() {
        String[] playHands = new String[numPlayers];
        int count = 0;

        for (PokerHand value : playerHands.values()) {
            playHands[count] = value.getCard1() + " " + value.getCard2();
            count++;
        }
        return playHands;
    }

    public String calcHandStrength(PokerHand person, String[] currentBoard) {
        boolean flush = false;
        boolean straight = false;
        boolean straightFlush = false;
        int highCardS3 = 0;
        String[] suits = new String[7];

        int highCardF = 0;
        String highestSuit = "";
        String[] sevenCards =
            {person.getCard1(), person.getCard2(), currentBoard[0], currentBoard[1], currentBoard[2], currentBoard[3],
                currentBoard[4]};
        int[] converted = new int[8];

        for (int i = 0; i < 7; i++) {    //Creates a new array with converted values
            converted[i] = convertInt(sevenCards[i]);
        }
        int maxSuitCount = 0;
        int[] suitCount = {0, 0, 0, 0};
        int[] numberSuits = new int[7];
        for (int i = 0; i < 7; i++) {   //Calculating Flush
            //0 is heart, 1 is diamond, 2 is spade, 3 is club

            if (sevenCards[i].contains("Heart")) {
                suitCount[0]++;
                if (suitCount[0] > maxSuitCount) {
                    maxSuitCount = suitCount[0];
                    highestSuit = "Heart";
                }
            } else if (sevenCards[i].contains("Diamond")) {
                suitCount[1]++;
                if (suitCount[1] > maxSuitCount) {
                    maxSuitCount = suitCount[1];
                    highestSuit = "Diamond";
                }
            } else if (sevenCards[i].contains("Spade")) {
                suitCount[2]++;
                if (suitCount[2] > maxSuitCount) {
                    maxSuitCount = suitCount[2];
                    highestSuit = "Spade";
                }
            } else {
                suitCount[3]++;
                if (suitCount[3] > maxSuitCount) {
                    maxSuitCount = suitCount[3];
                    highestSuit = "Club";
                }
            }

            if (maxSuitCount >= 5)
                flush = true;

            if (flush) {
                //Suits array holds all the suits that contain the flush
                int z = 0;
                for (int j = 0; j < 7; j++) {

                    if (sevenCards[j].contains(highestSuit)) {
                        suits[z] = sevenCards[j];
                        z++;
                    }
                }

                //Now we must find largest value in suits to find highCardF
                int max = 0;
                for (int l = 0; l < suits.length; l++) {
                    if (!(suits[l] == null)) {
                        numberSuits[l] = convertInt(suits[l]);
                    }
                    if (suits[l] != null && convertInt(suits[l]) > max) {
                        max = convertInt(suits[l]);
                    }
                }
                highCardF = max;
                break;
            }

        }
        //End of Flush calculation



        //Start of Straight Calculation
        Arrays.sort(converted);
        if (converted[7] == 14) {  //If Ace exists, add a 1 to the beginning
            converted[0] = 1;
            Arrays.sort(converted);  //Must sort again to bring 1 back to front
        }
        int straightCounter = 0;

        for (int i = 0; i < converted.length - 1; i++) {

            if (!(converted[i] + 1 == converted[i + 1])) {   //Checks if the next number is indeed only one higher
                straightCounter = 0;
            } else {
                straightCounter++;
            }
            if (straightCounter == 4) {
                straight = true;
                highCardS3 = converted[i + 1];
                //No break here because a higher flush is STILL possible
            } else if (straightCounter == 5) {
                highCardS3 = converted[i + 1];
                //Rare situation when the person has a "6 card straight"
            } else if (straightCounter == 6) {
                highCardS3 = converted[i + 1];
                //Very Rare situation when person has a "7 card straight"
            } else {
                //Continue with calculation
            }
        }
        //End of Straight Calculation



        if (flush && straight) {  //NOT NECESSARY STRAIGHT FLUSH
            int SFCounter = 0;  //This is checking if there is a straight in the "suits" array
            int highSF3 = 0;
            Arrays.sort(numberSuits);

            for (int i = 0; i < numberSuits.length - 1; i++) {

                if (!((numberSuits[i]) + 1 == numberSuits[i
                    + 1])) {   //Checks if the next number is indeed only one higher
                    SFCounter = 0;
                } else {
                    SFCounter++;
                }
                if (SFCounter == 4) {
                    straightFlush = true;
                    highSF3 = numberSuits[i + 1];
                    //No break here because a higher straight flush is STILL possible
                } else if (SFCounter == 5) {
                    highSF3 = numberSuits[i + 1];
                    //Rare situation when the person has a "6 card straight"
                } else if (SFCounter == 6) {
                    highSF3 = numberSuits[i + 1];
                    //Very Rare situation when person has a "7 card straight"
                } else {
                    //Continue with calculation
                }
            }
            if (straightFlush) {
                if (highSF3 == 14)
                    return "ROYALFLUSH";
                return "StraightFlush -" + highSF3 + "- high";

            }
        }
        //Calculating 4 of a kind -> only numbers matter (not suits)
        //We can use a Hashmap to do this

        Map<Integer, Integer> numFrequency = new HashMap<>();   //HashMap contains the frequencies of all the numbers
        //value of the number to frequency
        boolean three = false;
        int maxThree = 0;   //Can have up to two "three of a kinds"
        int threeCount = 0;
        int secondHighThree = 0;
        boolean two = false;
        boolean twoPair = false;
        int twoPairCount = 0;
        int maxTwo = 0;  //Can have up to three "two of a kinds"
        int secondMaxTwo = 0;  //For two pair, you take two highest pairs
        int highCard = 0;
        for (int i = 0; i < converted.length; i++) {  //calculates frequency of all the numbers
            if (!numFrequency.containsKey(converted[i])) {
                numFrequency.put(converted[i], 1);
            } else {
                numFrequency.put(converted[i], numFrequency.get(converted[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : numFrequency.entrySet()) {
            if (entry.getValue() == 4) {
                return "Four of a Kind -" + (entry.getKey()) + "-";
            } else if (entry.getValue() == 3) {
                threeCount++;
                three = true;
                if (maxThree < entry.getKey()) {
                    secondHighThree = maxThree;
                    maxThree = entry.getKey();
                } else if (secondHighThree < entry.getKey()) {
                    secondHighThree = entry.getKey();
                } else {

                }
            } else if (entry.getValue() == 2) {
                twoPairCount++;
                if (twoPairCount >= 2) {
                    twoPair = true;
                }
                two = true;
                if (maxTwo < entry.getKey()) {
                    secondMaxTwo = maxTwo;
                    maxTwo = entry.getKey();

                } else {
                    if (secondMaxTwo < entry.getKey()) {
                        secondMaxTwo = entry.getKey();
                    }
                }
            } else {
                //no dups
                if (highCard < entry.getKey()) {
                    highCard = entry.getKey();
                }
            }
        }
        if (three && two) {
            return "FullHouse --" + (maxThree) + "-- over -" + (maxTwo) + "-";   //Full house
        }
        if (threeCount == 2) {
            return "FullHouse --" + (maxThree) + "-- over " + (secondHighThree) + "-";
        }
        if (flush) {
            String[] convertSuits = suits.clone();
            int[] numSuits = new int[7];
            for (int i = 0; i < 7; i++) {
                numSuits[i] = convertInt(convertSuits[i]);
            }
            Arrays.sort(numSuits);
            return "Flush -" + numSuits[6] + "- -" + numSuits[5] + "- -" + numSuits[4] + "- -" + numSuits[3] + "- -"
                + numSuits[2] + "-";
        }
        if (straight)
            return "Straight -" + (highCardS3) + "- high";
        if (three) {
            int[] returnStatement = new int[5]; //The five cards considered
            int arrayChecker = 7;
            int arrayCounter = 0;
            while (arrayCounter < 2) {
                if (converted[arrayChecker] == maxThree) {
                    arrayChecker--;
                } else {
                    returnStatement[arrayCounter] = converted[arrayChecker];
                    arrayCounter++;
                    arrayChecker--;
                }
            }
            returnStatement[2] = maxThree;
            returnStatement[3] = maxThree;
            returnStatement[4] = maxThree;

            return "Three of a Kind " + convertStr(maxThree) + "s: -" + returnStatement[0] + "- -" + returnStatement[1]
                + "- -" + returnStatement[2] + "- -" + returnStatement[3] + "- -" + returnStatement[4] + "-";
        }
        if (twoPair) {
            int twoPairExtra;
            int twoPairArrayCounter = 7;
            while (true) {
                if (converted[twoPairArrayCounter] == maxTwo || converted[twoPairArrayCounter] == secondMaxTwo) {
                    twoPairArrayCounter--;
                } else {
                    twoPairExtra = converted[twoPairArrayCounter];
                    break;
                }
            }
            return "Two Pair --" + (maxTwo) + "--" + " and -" + (secondMaxTwo) + "-        " + twoPairExtra + "      ";
        }
        if (two) {
            int[] twoReturn = new int[5];
            int twoArrayChecker = 7;
            int twoArrayCounter = 0;
            while (twoArrayCounter < 3) {
                if (converted[twoArrayChecker] == maxTwo) {
                    twoArrayChecker--;
                } else {
                    twoReturn[twoArrayCounter] = converted[twoArrayChecker];
                    twoArrayCounter++;
                    twoArrayChecker--;
                }
            }
            twoReturn[3] = maxTwo;
            twoReturn[4] = maxTwo;
            return "One Pair " + convertStr(maxTwo) + "s -" + twoReturn[0] + "- -" + twoReturn[1] + "- -" + twoReturn[2]
                + "- -" + twoReturn[3] + "- -" + twoReturn[4] + "-";
        }

        return "High Card " + convertStr(highCard) + "s -" + converted[3] + "- -" + converted[4] + "- -" + converted[5]
            + "- -" + converted[6] + "- -" + converted[7] + "-";
        //BIG ERROR: MUST ADD DASHES
    }


    public void setPlayerHands(Map<Integer, PokerHand> hands) {
        this.playerHands = hands;
        for (Map.Entry<Integer, PokerHand> entry : playerHands.entrySet()) {
            counter =
                hands.size();  //Increase the counter so we don't override the initially set hand when we generate them
            if (usedHands.size() < 52) {
                usedHands.add(entry.getValue().getCard1());
                usedHands.add(entry.getValue().getCard2());
            } else {
                System.out.println("Not enough cards left to make more hands");
            }

        }

    }

    public Map<Integer, PokerHand> getMapPlayerHands() {
        return playerHands;
    }

    public void setCurrentBoard(String[] cards) { //WRONG COUNTER DOESNT NEED TO CHANGE
        if (cards.length > 5) {
            System.out.println("ERROR, can't have more than 5 cards on board");
        }
        currentBoard = cards;
        for (int i = 0; i < cards.length; i++) {
            if (currentBoard[i] != null) {
                usedHands.add(currentBoard[i]);   //Ensures that these cards in the board are put in usedHands
                numOfCardsBoard++;
            }

        }
    }

    public String[] getCurrentBoard() {
        return currentBoard;
    }



    public void newGame(int numPlayers) {
        usedHands = new ArrayList<>();
        playerHands = new HashMap<>();
        currentBoard = new String[5];
        counter = 0;
        this.numPlayers = numPlayers;
        numOfCardsBoard = 0;
    }

    public boolean checkCardAvaliable(PokerHand check) {
        if (usedHands.contains(check.getCard1()) || usedHands.contains(check.getCard2())) {
            return false;
        } else {
            return true;
        }
    }


}
