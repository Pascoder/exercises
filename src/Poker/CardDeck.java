package Poker;

import java.util.ArrayList;

public class CardDeck {
	
	final static int TOTAL_CARDS = 52;
	
	public CardDeck() {
		ArrayList<PokerCard> cardArrayList = new ArrayList<PokerCard>();
		CardDeck.deckGenerator(cardArrayList);
		}
	
	
	
	public static ArrayList<PokerCard> deckGenerator(ArrayList<PokerCard> cardArrayList) {
		
		int cardNumber = 0;
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Clubs");
			
				PokerCard pc = new PokerCard(s,i);
				cardArrayList.add(cardNumber,pc);
				cardNumber ++;
//				System.out.println(pc);		
		}
	
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Hearts");
			
				PokerCard pc = new PokerCard(s,i);
				cardArrayList.add(cardNumber,pc);
				cardNumber ++;
//				System.out.println(pc);

		}
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Spades");
			
				PokerCard pc = new PokerCard(s,i);
				cardArrayList.add(cardNumber,pc);
				cardNumber ++;
//				System.out.println(pc);

		}
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Diamonds");
			
				PokerCard pc = new PokerCard(s,i);
				cardArrayList.add(cardNumber,pc);
				cardNumber ++;
//				System.out.println(pc);

		}
	
		return cardArrayList;
		
	
	}







			
		
		
	}
	
	


