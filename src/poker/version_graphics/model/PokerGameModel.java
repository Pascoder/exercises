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
		//Array List wird sortiert
		Collections.sort(clone);
		Collections.reverse(clone);
		
		int handTypeBestPlayer = clone.get(0).getHandType().ordinal();
		//Wenn die beiden besten Hände gleich sind, wird gezählt wieviele Hände insgesamt gleich sind
		if(clone.get(0).getHandType().equals(clone.get(1).getHandType())) {
			//Herausfinden welche Handtypes die gleichen Hände haben
			for(int i = 0;i <clone.size()-1;i ++) {
				
				if (clone.get(i).getHandType().compareTo(clone.get(i+1).getHandType())==0 )  {
				sameHandType++;
				} 
			}
			
			
			switch(handTypeBestPlayer) {
			//zwei oder mehrere Spieler haben Highcards
			case 0: 
				
				winnerName = clone.get(0).getPlayerName()+  " --- Hand: " + clone.get(0).getHandType().toString();
				
				for(int i=0; i < sameHandType;i++) {
					for(int j = 0;j < Player.HAND_SIZE;j++) {
					
						if(clone.get(i).getCards().get(j).compareTo(clone.get(0).getCards().get(0)) == 1)
							winnerName = clone.get(i).getPlayerName()+  " --- Hand: " + clone.get(i).getHandType().toString();	
						}
				}
			
			break;
			//zwei oder mehrere Spieler haben onePair
			case 1:
				int highest=-1;
				winnerName = clone.get(0).getPlayerName()+  " --- Hand: " + clone.get(0).getHandType().toString();
				
				for(int i=0; i < sameHandType;i++) {
					for(int j = 0;j < Player.HAND_SIZE-1;j++) {
						
						if(clone.get(i).getCards().get(j).compareTo(clone.get(i).getCards().get(j+1))==0 && 
								clone.get(i).getCards().get(j).getRank().ordinal() > highest) {
								highest = clone.get(i).getCards().get(j).getRank().ordinal();
								winnerName = clone.get(i).getPlayerName()+  " --- Hand: " + clone.get(i).getHandType().toString();	
						}	
					}
				}
				
				
			break;
			
			
			//zwei oder mehrere Spieler haben twoPairs
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
				
				
				System.out.println("Mehrere TwoPairs");
			
			
			
			}
			
			
			
			
			} else winnerName = clone.get(0).getPlayerName() + " --- Hand: " + clone.get(0).getHandType().toString();
			
		
			
			
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