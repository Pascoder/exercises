package Poker;

public class PokerCard {
	

//	public cardSuit cardSuit;
	private int cardRank;
	public String suit;
	
//	public enum cardSuit {HEARTS, SPADES, CLUBS, DIAMONDS};
	
	public PokerCard(String suit, int cardRank) {
		
		this.cardRank = cardRank;
		this.suit = suit;
		
		
		
	}

	@Override
	public String toString() {
		return "PokerCard [cardRank=" + cardRank + ", suit=" + suit + "]";
	}

	
}
	


