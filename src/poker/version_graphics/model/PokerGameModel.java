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
	
	public String evaluateWinner() {
/**	Methode klont die ArrayList<Player> und sortiert diese anhand des HandType der Spieler.
 * Wenn der HandType der letzten beiden in der Liste gleich ist, wird ermittelt wer die höhere Hand hat
 */
		
		ArrayList <Player> clone = (ArrayList<Player>) players.clone();
		
		Collections.sort(clone);
		
		HandType firstHand = clone.get(clone.size()-1).getHandType();
		HandType secondHand = clone.get(clone.size()-2).getHandType();
		
		if(firstHand.equals(secondHand)) {
			if(firstHand.ordinal()==0) {
				int j = clone.get(0).getCards().get(Player.HAND_SIZE-1).compareTo(clone.get(1).getCards().get(Player.HAND_SIZE-1));
				if(j==1) {
					winnerName=clone.get(0).getPlayerName();
				} else
					if(j==-1) {
						winnerName=clone.get(1).getPlayerName();
					} 
			}
			
			
		}else winnerName = clone.get(clone.size()-1).getPlayerName();
		
		return winnerName;
		

	}
	
	
}