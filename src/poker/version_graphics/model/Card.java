package poker.version_graphics.model;

import java.util.ArrayList;

public class Card implements Comparable<Card>{
    public enum Suit { Clubs, Diamonds, Hearts, Spades;
        @Override
        public String toString() {
            String suit = "";
            switch (this) {
            case Clubs: suit = "clubs"; break;
            case Diamonds: suit = "diamonds"; break;
            case Hearts: suit = "hearts"; break;
            case Spades: suit = "spades"; break;
            }
            return suit;
        }
    };
    
    public enum Rank { Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
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
    };
    
    private final Suit suit;
    private final Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
     
    //Sortiert ArrayList von Card Objekten
    public static ArrayList<Card> sortCards(ArrayList<Card> cards){
    	while (cards.size() > 0) {
            Card highestCard = cards.get(0);
            for (int i = 1; i < cards.size(); i++)
                if (cards.get(i).getRank().ordinal() > highestCard.getRank().ordinal() ||
                        cards.get(i).getRank().ordinal() == highestCard.getRank().ordinal() && cards.get(i).getSuit().ordinal() > highestCard.getSuit().ordinal())
               	 highestCard = cards.get(i);
            cards.remove(highestCard);
            cards.add(highestCard);
        }
    	return cards;
    }
    
 
  
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

	@Override
	public int compareTo(Card otherCard) {
		if ( this.rank.ordinal() > otherCard.rank.ordinal())
			return 1;
		else
			if (this.rank.ordinal()<otherCard.rank.ordinal())
				return -1;
			else
				return 0;
	}
    
}
