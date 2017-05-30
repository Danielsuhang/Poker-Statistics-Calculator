package poker;

public class PokerHand {
	private String card1;
	private String card2;
	
	public PokerHand(String card1, String card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	
	public String getCard1() {
		return card1;
	}
	public String getCard2() {
		return card2;
	}
	public void setCard1(String card1) {
		this.card1 = card1;
	}
	public void setCard2(String card2) {
		this.card2 = card2;
	}

}
