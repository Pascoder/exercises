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
		//Array List wird sortiert
		Collections.sort(clone);
		
		int handTypeBestPlayer = clone.get(clone.size()-1).getHandType().ordinal();
		//Wenn die beiden besten Hände gleich sind, wird gezählt wieviele Hände insgesamt gleich sind
		if(clone.get(clone.size()-1).getHandType().equals(clone.get(clone.size()-2).getHandType())) {
		//Wieviele Spieler haben die gleiche Hand?
			for(int i = clone.size()-1;i >= 1;i --) {
				
				if (clone.get(i).compareTo(clone.get(i-1))==0 )  {
				sameHandType++;
				} 
			}
			
			//Herausfinden welche Handtypes die gleichen Hände haben
			switch(handTypeBestPlayer) {
			//Highcards
			case 0: System.out.println("Mehrere Highcards");
			//Durch alle Hände und alle Karten iterieren
			for(int i = 0; i<sameHandType;i++ ) {
				for(int j=0;j<Player.HAND_SIZE;j++) {
				
				
				
				
				
//				System.out.println("Karte: "+j +"von Spieler: " +i);
				} System.out.println("Spieler : " + i);
			}
			break;
			//onePairs	
			case 1: System.out.println("Mehrere Onepairs");
			break;
			//twoPairs
			case 2: System.out.println("Mehrere TwoPairs");
			}
			
			
			
			
			} else winnerName = clone.get(clone.size()-1).getPlayerName();
			
		
			
			
			sameHandType=1;
		
		
			
			
		
		
//		HandType firstHandType = clone.get(clone.size()-1).getHandType();
//		HandType secondHandType = clone.get(clone.size()-2).getHandType();
//		Card firstPlayerBestCard = clone.get(0).getCards().get(Player.HAND_SIZE-1);
//		Card secondPlayerBestCard = clone.get(1).getCards().get(Player.HAND_SIZE-1);
//		
		
		
		
		return winnerName;
		
//		if(firstHandType.equals(secondHandType)) {
//				int j = firstPlayerBestCard.compareTo(secondPlayerBestCard);
//				if(j==1) {
//					winnerName=clone.get(0).getPlayerName();
//				} else
//					if(j==-1) {
//						winnerName=clone.get(1).getPlayerName();
//					} 
//			} else winnerName = clone.get(clone.size()-1).getPlayerName();
//		
//		return winnerName;
//		
//
	}
//	
//	
}