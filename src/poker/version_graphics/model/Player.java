package poker.version_graphics.model;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
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

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }
    
  

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
        return handType.compareTo(o.handType);
    }
}
