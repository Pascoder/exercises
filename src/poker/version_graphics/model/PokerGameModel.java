package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

import poker.version_graphics.PokerGame;
import poker.version_graphics.view.PokerGameView;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private String winnerName;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
//	public String evaluateWinner() {
	//Methode soll die ArrayList<Player> sortieren und dann den ersten als Gewinner ausgeben--> ToDO!!!
//		String WinnerName;
//		ArrayList <Player> clone = (ArrayList<Player>) (players).clone();
//		Collections.sort(clone);
//		if(clone.get(0).compareTo(clone.get(1)) == 1) {
//			return clone.get(0).getPlayerName();
//		}else 
//			return clone.get(1).getPlayerName();
//		
//	
//		
//	}
}
