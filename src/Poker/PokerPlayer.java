package Poker;

import java.util.ArrayList;



public class PokerPlayer {
	private String playerName;
	private PokerCard [] hand;
	private HandType handType;
		
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
	
	
public HandType evaluateHand(PokerCard[] hand) {
	//Method which evaluates the actual hand and returns a HandType
	
	
	return null;
}
	
	
	
	
	
	
	
	
	}



