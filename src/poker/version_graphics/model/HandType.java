package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

import poker.version_graphics.model.Card.Rank;
import poker.version_graphics.model.Card.Suit;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {

    	
        HandType currentEval = HighCard;

        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        if (isRoyalFlush(cards)) currentEval = RoyalFlush;
        
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
    
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for(int k = j+1;k<cards.size() && !found;k++) {
                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank()== cards.get(k).getRank() ) 
                found = true;
            		
                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank()== cards.get(k).getRank() ) 
                found = true;
                
            	}
            } 	
        }
        return found;
    }
    	

    public static boolean isStraight(ArrayList<Card> cards) {
    	boolean found = false;
    		
    	//Sortiert alle Karten in der Hand, nach Rank
    	Collections.sort(cards);
    	//Hoechste Karte kommt zuerst
    	Collections.reverse(cards);
    		
        if  	(	cards.get(0).getRank().ordinal() == cards.get(1).getRank().ordinal() + 1
        			&& cards.get(1).getRank().ordinal() == cards.get(2).getRank().ordinal() + 1
                    && cards.get(2).getRank().ordinal() == cards.get(3).getRank().ordinal() + 1
                    && cards.get(3).getRank().ordinal() == cards.get(4).getRank().ordinal() + 1
        		) 
            
        found = true;

        return found;
    }
    
    	
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
    
    
    //Prüft ob die ersten beiden oder die ersten drei karten gleich sind
    public static boolean isFullHouse(ArrayList<Card> cards) {
    	boolean found = false;
    	boolean twoFirst, threeFirst;
    	
    	twoFirst = cards.get(0).getRank()==cards.get(1).getRank()&&
    				cards.get(1).getRank()==cards.get(2).getRank()&&
    				cards.get(3).getRank()==cards.get(4).getRank();
    	
    	threeFirst = cards.get(0).getRank()==cards.get(1).getRank()&&
    				cards.get(2).getRank()==cards.get(3).getRank()&&
    				cards.get(3).getRank()==cards.get(4).getRank();
    	
    	if (threeFirst || twoFirst) found=true;	
    	       
        return found;
    }
    
    
    
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
    	boolean four = false;
    	int i = 0;
    	int b = 1;
    	int c = 2;
    	int d = 3;
    	
    	if(cards.get(i).getRank()==cards.get(b).getRank()&& cards.get(b).getRank() == cards.get(c).getRank()&&cards.get(c).getRank() == cards.get(d).getRank()) {
    		four = true;
    	}else {
    		d++;
    		if(cards.get(i).getRank()==cards.get(b).getRank()&& cards.get(b).getRank() == cards.get(c).getRank()&&cards.get(c).getRank() == cards.get(d).getRank()) {
    			four = true;
    		}else {
    			b++; c++;
    			if(cards.get(i).getRank()==cards.get(b).getRank()&& cards.get(b).getRank() == cards.get(c).getRank()&&cards.get(c).getRank() == cards.get(d).getRank()) {
    				four = true;
    			}else {
    				i++;
    				if(cards.get(i).getRank()==cards.get(b).getRank()&& cards.get(b).getRank() == cards.get(c).getRank()&&cards.get(c).getRank() == cards.get(d).getRank()) {
    					four = true;
    				}
    			}
    		}
    	}
        return four;
    }
    
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
    	boolean found=false;
       if (isFlush(cards) && isStraight(cards)) {
    	   found=true;
       }
    return found;
    }
    
    
    public static boolean isRoyalFlush(ArrayList<Card> cards) {
    	boolean found = false;
    	if(isStraightFlush(cards) && cards.get(1).getRank()== Rank.Ace) {
    		found=true;
    	}
    	return found;
    } 



}
