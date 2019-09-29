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

	public int getCardRank() {
		return cardRank;
	}

	public String getSuit() {
		return suit;
	}

	public void setCardRank(int cardRank) {
		this.cardRank = cardRank;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	
}
	


