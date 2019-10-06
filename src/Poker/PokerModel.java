package Poker;



import java.util.ArrayList;

import Poker.PokerPlayer.HandType;



public class PokerModel {
	
	private final ArrayList<PokerPlayer> players = new ArrayList<>();
	private CardDeck deck;
	
	
	public PokerModel() {
		for (int i = 0; i < PokerMVC.NUMBER_OF_PLAYERS; i++) {
			players.add(new PokerPlayer("Player " + i));
		}
		
		deck = new CardDeck();	

	}
	
	


	}
	/*public Image[] shuffle(Image[] imageArray) {

	

		return imageArray;	

	}*/



