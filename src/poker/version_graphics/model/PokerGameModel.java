package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

import poker.version_graphics.PokerGame;
import poker.version_graphics.view.EnterMenu;
import poker.version_graphics.view.PokerGameView;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private String winnerName;
	private int sameHandType = 1;
	private ArrayList<Player> sameHands;
	
	
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player(EnterMenu.playersname.get(i)));
			
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	/**Methode klont die ArrayList<Player> und sortiert diese anhand des HandType der Spieler.
	 * Wenn der HandType der letzten beiden in der Liste gleich ist, wird ermittelt wer die höhere Hand hat
	 */
	public String evaluateWinner() {

		ArrayList <Player> clone = (ArrayList<Player>) players.clone();
		//sort Array List using handTypes
		Collections.sort(clone);
		Collections.reverse(clone);
		//Which is the best handType?
		int handTypeBestPlayer = clone.get(0).getHandType().ordinal();
		//Counts how many best players having the same hand
		if(clone.get(0).getHandType().equals(clone.get(1).getHandType())) {
			//
			for(int i = 0;i <clone.size()-1;i ++) {
				
				if (clone.get(i).getHandType().compareTo(clone.get(i+1).getHandType())==0 )  {
				sameHandType++;
				} 
			}
			
			
			switch(handTypeBestPlayer) {
			//two or more players have Highcard
			case 0: 
				
				winnerName = clone.get(0).getPlayerName()+  " --- Hand: " + clone.get(0).getHandType().toString();
				
				for(int i=0; i < sameHandType;i++) {
					for(int j = 0;j < Player.HAND_SIZE;j++) {
					
						if(clone.get(i).getCards().get(j).compareTo(clone.get(0).getCards().get(0)) == 1)
							winnerName = clone.get(i).getPlayerName()+  " --- Hand: " + clone.get(i).getHandType().toString();	
						}
				}
			
			break;
			//two or more players have onePair
			case 1:
				int highest=-1;
				winnerName = clone.get(0).getPlayerName()+  " --- Hand: " + clone.get(0).getHandType().toString();
				
				for(int i=0; i < sameHandType;i++) {
					for(int j = 0;j <Player.HAND_SIZE-1;j++) {
						
						if(clone.get(i).getCards().get(j).compareTo(clone.get(i).getCards().get(j+1))==0 && 
								clone.get(i).getCards().get(j).getRank().ordinal() > highest) {
								highest = clone.get(i).getCards().get(j).getRank().ordinal();
								winnerName = clone.get(i).getPlayerName()+  " --- Hand: " + clone.get(i).getHandType().toString();	
						}	
					}
				}
				
				
			break;
			
			
			//two or more players have twoPairs
			case 2: 
				
				int highest2=-1;
				winnerName = clone.get(0).getPlayerName()+  " --- Hand: " + clone.get(0).getHandType().toString();
				
				for(int i=0; i < sameHandType;i++) {
					for(int j = 0;j < Player.HAND_SIZE-1;j++) {
						
						if(clone.get(i).getCards().get(j).compareTo(clone.get(i).getCards().get(j+1))==0 && 
								clone.get(i).getCards().get(j).getRank().ordinal() > highest2) {
							highest2 = clone.get(i).getCards().get(j).getRank().ordinal();
							winnerName = clone.get(i).getPlayerName() +  " --- Hand: " + clone.get(i).getHandType().toString();
						}
					}
				}
				
			}
		
			
			} else winnerName = clone.get(0).getPlayerName() + " --- Hand: " + clone.get(0).getHandType().toString();
		
			sameHandType=1;
			return winnerName;

	}
	
}