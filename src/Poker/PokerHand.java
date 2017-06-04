package poker;


public class PokerHand {
	private String card1;
	private String card2;

	private boolean rangeSuited = false;



	public PokerHand(String card1, String card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	public PokerHand (String card1, String card2, boolean rangeSuited) {
		this(card1, card2);
		if (card1 != card2) {
			this.rangeSuited = rangeSuited;  //Suited variable only used for defining Poker Ranges
		}
		else {
			System.err.println("You cannot have two suited cards when the two cards are the same");
		}

	}
	public boolean getSuited() {
		return rangeSuited;
	}
	public String getCard1() {
		return card1;
	}
	public void setCard1(String card1) {
		this.card1 = card1;
	}
	public String getCard2() {
		return card2;
	}
	public void setCard2(String card2) {
		this.card2 = card2;
	}


}
