package Poker;



public class PokerCard {
	private final CardRank cardRank;
	private final CardSuit cardSuit;
//	public Image images; //Moeglichkeit bieten Pokerkarten ein Bild zu hinterlegen
	

	public PokerCard(CardRank cardRank, CardSuit cardSuit) {
		
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}
	
	public enum CardSuit {
		HEARTS, 
		SPADES, 
		CLUBS, 
		DIAMONDS;
		
		@Override
		public String toString() {
			String suit = "";
            switch (this) {
            case CLUBS: suit = "clubs"; break;
            case DIAMONDS: suit = "diamonds"; break;
            case HEARTS: suit = "hearts"; break;
            case SPADES: suit = "spades"; break;
            }
            return suit;

		}
	}
	
	
	public enum CardRank {
		TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN,
		JACK, QUEEN, KING, ACE;
		
		@Override
        public String toString() {
            String str = "ace";  // Assume we have an ace, then cover all other cases
            // Get ordinal value, which ranges from 0 to 12
            int ordinal = this.ordinal();
            if (ordinal <= 8) {
                str = Integer.toString(ordinal+2);
            } else if (ordinal == 9) { // Jack
                str = "jack";
            } else if (ordinal == 10) { // Queen
                str = "queen";
            } else if (ordinal == 11) { // King
                str = "king";
            }
            return str;
        }

	}
	
	public String toString(){
   		return cardSuit.name()+"-"+cardRank.name();
	}
//	public  void addImage(Image img) {
//		this.images = img;
//	}
	
	public CardRank getCardRank() {
		return this.cardRank;	
	}
	
	public CardSuit getCardSuit() {
		return this.cardSuit;
	}
	
}
	


