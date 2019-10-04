package Poker;

import java.awt.List;
import java.util.ArrayList;



public class PokerPlayer {
	private String playerName;
	private PokerCard [] hand;
	private HandType handType;
	private final int handSize = 5;
		
	public enum HandType{
			
			HIGHCARD,
			PAIR,
			TWOPAIR,
			THREEOFAKIND,
			STRAIGHT,
			FLUSH,
			FULLHOUSE,
			FOUROFAKIND,
			STRAIGHTFLUSH,
			ROYALFLUSH
			
		}
	
	
public PokerPlayer(int a) {
	this.playerName = "Player " + a;

	}
public void setName(String name) {
	this.playerName = name;
}
//CardDeck cd ist ein Objekt das ein ArrayListe enthält
public PokerCard[] generateHand(CardDeck cd) {
	 this.hand = new PokerCard[this.handSize];
	
	
	for(int i = 0; i<handSize;i++) {
	 
		hand[i] = cd.getArrayList().get(i);//Funktioniert
		
		
		
	}
	for(int b = 0; b<handSize;b++) {
		System.out.println(hand[b]);
	}
	return hand;
	
	
}
public void setHandType(HandType handtype) {
	this.handType = handtype;
}
public String getName() {
	return this.playerName;
}
//nur für den Test benötigt um Hand anzuschauen

public HandType getHandtype() {
	return this.handType;
}
	
	

	
	
	
	
	
	
	
	
	}



