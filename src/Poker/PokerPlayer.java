package Poker;

import java.util.ArrayList;



public class PokerPlayer {
	private String playerName;
	private PokerCard [] hand;
	private HandType handType;
	public static final int HANDSIZE = 5;
		
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
	
	
public PokerPlayer(String s) {
	this.playerName = "Player " + s;

	}
public void setName(String name) {
	this.playerName = name;
}
//CardDeck cd ist ein Objekt das ein ArrayListe enthaelt

public PokerCard[] generateHand(CardDeck cd) {
	 this.hand = new PokerCard[HANDSIZE];
	
	
	for(int i = 0; i<HANDSIZE;i++) {
	 
		hand[i] = cd.getArrayList().get(i);//Funktioniert
		
		
		
	}
	
	//Test ob es Funktioniert kann nachher geloescht werden
	System.out.println("Created Hands:");
	for(int b = 0; b<HANDSIZE;b++) {
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
//nur fuer den Test benoetigt um Hand anzuschauen

public HandType getHandtype() {
	return this.handType;
}
	
public PokerCard[] getHand() {
	return this.hand;
}

	
	
	
	
	
	
	
	
	}



