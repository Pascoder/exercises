package Poker;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;




public class CardDeck {
	
	private ArrayList<PokerCard> cardArrayList;
	
	
	public CardDeck() {
		ArrayList<PokerCard> cardArrayList = new ArrayList<PokerCard>();
		
		
		PokerCard.CardRank[] ranks = PokerCard.CardRank.values();  
        PokerCard.CardSuit[] suits = PokerCard.CardSuit.values(); 

        for (PokerCard.CardSuit suit : suits) {
            for (PokerCard.CardRank rank : ranks) {
            	cardArrayList.add(new PokerCard(rank, suit));
            }
        }
		
        this.cardArrayList = cardArrayList;
        
	}  
        
        
	
	//Methode um Karten zu mischen
	public CardDeck shuffleDeck(CardDeck cd) {
		Collections.shuffle(cd.cardArrayList);
        	return cd;  
        	
	}
	
	//Test Methode um alle Karten im Deck in der Konsole auszugeben
	public void printDeck(CardDeck cd) {
		int n = cardArrayList.size();

        for (int i = 0; i < n ; i++) {
            System.out.println((i + 1)  + ": " + cardArrayList.get(i));
		}
	}
	public ArrayList<PokerCard> getArrayList(){
		return this.cardArrayList;
	}
	
		
		
}
	
	


