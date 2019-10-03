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
	
	
	
	boolean flush = true; //assume there is a flush
	
	for (int x=0; x<6; x++)
	      {
	            if ( hand[x].cardSuit.ordinal()  != hand[x+1].cardSuit.ordinal() )

	                flush = false;
	        }

	
	
	return null;
}
	
	
	
	
	
	
	
	
	}



