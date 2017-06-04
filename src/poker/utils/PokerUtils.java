package poker.utils;

public class PokerUtils {
    private PokerUtils() {
        // static only class, can't instantial from outside
    }

    public static int convertInt(String num) {
        if (num == null) return -1;
        String lower = num.toLowerCase();
        if (lower.contains("ace") || num.contains("14")) return 14;   //Acts as essentially 1 or 14 in straight but only 14 in flush
        if (lower.contains("ten") || num.contains("10")) return 10;
        if (lower.contains("jack") || num.contains("11")) return 11;
        if (lower.contains("queen") || num.contains("12")) return 12;
        if (lower.contains("king") || num.contains("13")) return 13;
        if (lower.contains("two") || num.contains("2")) return 2;
        if (lower.contains("three") || num.contains("3")) return 3;
        if (lower.contains("four") || num.contains("4")) return 4;
        if (lower.contains("five") || num.contains("5")) return 5;
        if (lower.contains("six") || num.contains("6")) return 6;
        if (lower.contains("seven") || num.contains("7")) return 7;
        if (lower.contains("eight") || num.contains("8")) return 8;
        if (lower.contains("nine") || num.contains("6")) return 9;

        return -1;
    }

    public static int convertStrAdvanced(String num) {
        if (num == null) return -1;
        String lower = num.toLowerCase();
        if (lower.contains("aces")) return 14;   //Acts as essentially 1 or 14 in straight but only 14 in flush
        if (lower.contains("twos")) return 2;
        if (lower.contains("threes")) return 3;
        if (lower.contains("fours")) return 4;
        if (lower.contains("fives")) return 5;
        if (lower.contains("sixs")) return 6;
        if (lower.contains("sevens")) return 7;
        if (lower.contains("eights")) return 8;
        if (lower.contains("nines")) return 9;
        if (lower.contains("tens")) return 10;
        if (lower.contains("jacks")) return 11;
        if (lower.contains("queens")) return 12;
        if (lower.contains("kings")) return 13;
        return -1;
    }

    public static String convertStr(int num) {
        if (num == 1 || num == 14) {
            return "Ace";
        }
        if (num == 2) return "Two";
        if (num == 3) return "Three";
        if (num == 4) return "Four";
        if (num == 5) return "Five";
        if (num == 6) return "Six";
        if (num == 7) return "Seven";
        if (num == 8) return "Eight";
        if (num == 9) return "Nine";
        if (num == 10) return "Ten";
        if (num == 11) return "Jack";
        if (num == 12) return "Queen";
        if (num == 13) return "King";
        return "ERROR"; //If num meets none of the above, error has occurred.
    }

    public static int[] convertHighCards(String str) {
        int[] solution = new int[7];
        int arrayCounter = 0;
        if (str.contains("-14-")) {
            solution[arrayCounter] = 14;
            arrayCounter++;
        }
        if (str.contains("-13-")) {
            solution[arrayCounter] = 13;
            arrayCounter++;
        }
        if (str.contains("-12-")) {
            solution[arrayCounter] = 12;
            arrayCounter++;
        }
        if (str.contains("-11-")) {
            solution[arrayCounter] = 11;
            arrayCounter++;
        }
        if (str.contains("-10-")) {
            solution[arrayCounter] = 10;
            arrayCounter++;
        }
        if (str.contains("-9-")) {
            solution[arrayCounter] = 9;
            arrayCounter++;
        }
        if (str.contains("-8-")) {
            solution[arrayCounter] = 8;
            arrayCounter++;
        }
        if (str.contains("-7-")) {
            solution[arrayCounter] = 7;
            arrayCounter++;
        }
        if (str.contains("-6-")) {
            solution[arrayCounter] = 6;
            arrayCounter++;
        }
        if (str.contains("-5-")) {
            solution[arrayCounter] = 5;
            arrayCounter++;
        }
        if (str.contains("-4-")) {
            solution[arrayCounter] = 4;
            arrayCounter++;
        }
        if (str.contains("-3-")) {
            solution[arrayCounter] = 3;
            arrayCounter++;
        }
        if (str.contains("-2-")) {
            solution[arrayCounter] = 2;
            arrayCounter++;
        }
        return solution;
    }

    public static int[] checkFullHouse(String fullHouse) {
        int[] returnFull = new int[2];
        //For the three of a kind that goes with a fullHouse
        if (fullHouse.contains("--14--")) {
            returnFull[0] = 14;
        }
        if (fullHouse.contains("--13--")) {
            returnFull[0] = 13;
        }
        if (fullHouse.contains("--12--")) {
            returnFull[0] = 12;
        }
        if (fullHouse.contains("--11--")) {
            returnFull[0] = 11;
        }
        if (fullHouse.contains("--10--")) {
            returnFull[0] = 10;
        }
        if (fullHouse.contains("--9--")) {
            returnFull[0] = 9;
        }
        if (fullHouse.contains("--8--")) {
            returnFull[0] = 8;
        }
        if (fullHouse.contains("--7--")) {
            returnFull[0] = 7;
        }
        if (fullHouse.contains("--6--")) {
            returnFull[0] = 6;
        }
        if (fullHouse.contains("--5--")) {
            returnFull[0] = 5;
        }
        if (fullHouse.contains("--4--")) {
            returnFull[0] = 4;
        }
        if (fullHouse.contains("--3--")) {
            returnFull[0] = 3;
        }
        if (fullHouse.contains("--2--")) {
            returnFull[0] = 2;
        }

        //For the pair that goes with a fullHouse
        if (fullHouse.contains(" -14-")) {
            returnFull[1] = 14;
        }
        if (fullHouse.contains(" -13-")) {
            returnFull[1] = 13;
        }
        if (fullHouse.contains(" -12-")) {
            returnFull[1] = 12;
        }
        if (fullHouse.contains(" -11-")) {
            returnFull[1] = 11;
        }
        if (fullHouse.contains(" -10-")) {
            returnFull[1] = 10;
        }
        if (fullHouse.contains(" -9-")) {
            returnFull[1] = 9;
        }
        if (fullHouse.contains(" -8-")) {
            returnFull[1] = 8;
        }
        if (fullHouse.contains(" -7-")) {
            returnFull[1] = 7;
        }
        if (fullHouse.contains(" -6-")) {
            returnFull[1] = 6;
        }
        if (fullHouse.contains(" -5-")) {
            returnFull[1] = 5;
        }
        if (fullHouse.contains(" -4-")) {
            returnFull[1] = 4;
        }
        if (fullHouse.contains(" -3-")) {
            returnFull[1] = 3;
        }
        if (fullHouse.contains(" -2-")) {
            returnFull[1] = 2;
        }
        return returnFull;
    }

    public static int numericalHandStrength (String handLevel) {
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
