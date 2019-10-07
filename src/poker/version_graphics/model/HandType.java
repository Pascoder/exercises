package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.model.Card.Rank;
import poker.version_graphics.model.Card.Suit;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        //vereinfachtes test Szenario
        /*cards.clear();
        Card karte1 = new Card(Suit.Clubs,Rank.Five);
        Card karte2 = new Card(Suit.Clubs,Rank.Six);
        Card karte3 = new Card(Suit.Clubs, Rank.Seven);
        Card karte4 = new Card(Suit.Clubs, Rank.Eight);
        Card karte5 = new Card(Suit.Clubs, Rank.Nine);
        cards.add(karte1);
        cards.add(karte2);
        cards.add(karte3);
        cards.add(karte4);
        cards.add(karte5);*/
        //
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    	//Created by Frank
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for(int k = j+1;k<cards.size() && !found;k++) {
                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank()== cards.get(k).getRank() ) 
                found = true;
            		
                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank()== cards.get(k).getRank() ) found = true;
                
            	}
            }
            	
        }
        return found;
    }
    	//Created by Pascoder

    public static boolean isStraight(ArrayList<Card> cards) {

    	boolean isStraight = false;
        if (cards.size() == 5) {
                  
                isStraight = cards.get(0).getRank().ordinal() == cards.get(1).getRank().ordinal() + 1
                            && cards.get(1).getRank().ordinal() == cards.get(2).getRank().ordinal() + 1
                            && cards.get(2).getRank().ordinal() == cards.get(3).getRank().ordinal() + 1
                            && cards.get(3).getRank().ordinal() == cards.get(4).getRank().ordinal() + 1;
            }
            
        return isStraight;

//    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
//    	String straight[] = {"5","6","7","8","9"};
//    	boolean found = false;
//    	int correctcards = 0;
//        for(int i = 0; i < clonedCards.size() - 1 &&!found;i++) {
//        	System.out.println("innen  "+clonedCards.get(i).getRank().toString());
//        	for(int b = 0; b< straight.length;b++ ) {
//        		System.out.println("aussen  "+straight[b]);
//        		if(clonedCards.get(i).getRank().toString() == straight[b]) { 
//        		correctcards++;
//        		
//        	
//        		}
//        	}
//        }
      
//        if(correctcards == 5)
//        found = true;
//        System.out.println(correctcards);
//		return found;
		

    }
    	//Created by Passcoder
    public static boolean isFlush(ArrayList<Card> cards) {
    	boolean isFlush = false;
        if (cards.size() == 5) {
            isFlush = cards.get(0).getSuit() == cards.get(1).getSuit() 
                            && cards.get(1).getSuit() == cards.get(2).getSuit()
                            && cards.get(1).getSuit() == cards.get(3).getSuit() 
                            && cards.get(1).getSuit() == cards.get(4).getSuit();
        }       
        return isFlush;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        // TODO        
        return false;
    }
}
