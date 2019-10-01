package Poker;

import java.util.ArrayList;

public class CardDeck {
	
	final static int TOTAL_CARDS = 52;
	private ArrayList<PokerCard> cardArrayList;
	
	
	public CardDeck() {
		ArrayList<PokerCard> cardArrayList = new ArrayList<PokerCard>();
		this.cardArrayList = CardDeck.generateDeck(cardArrayList);
		
		
		}
	
	
	//Seite 204 im Java Script! Im Kontruktor die Methode CardDeck.generateDeck() aufrufen und dann ein Objekt des Typs CardDeck 
	//erzeugen.
	
	public static ArrayList<PokerCard> generateDeck(ArrayList<PokerCard> cardArrayList) {
		
	//Add all PokerCards to cardArrayList in an ugly way!! how to do for-Loop through enum???	
		
	PokerCard pc1 = new PokerCard(CardRank.ACE,CardSuit.CLUBS);
	cardArrayList.add(pc1);
	
	PokerCard pc2 = new PokerCard(CardRank.TWO,CardSuit.CLUBS);
	cardArrayList.add(pc2);
	
	PokerCard pc3 = new PokerCard(CardRank.THREE,CardSuit.CLUBS);
	cardArrayList.add(pc3);
	
	PokerCard pc4 = new PokerCard(CardRank.FOUR,CardSuit.CLUBS);
	cardArrayList.add(pc4);
	
	PokerCard pc5 = new PokerCard(CardRank.FIVE,CardSuit.CLUBS);
	cardArrayList.add(pc5);
	
	PokerCard pc6 = new PokerCard(CardRank.SIX,CardSuit.CLUBS);
	cardArrayList.add(pc6);
	
	PokerCard pc7 = new PokerCard(CardRank.SEVEN,CardSuit.CLUBS);
	cardArrayList.add(pc7);
	
	PokerCard pc8 = new PokerCard(CardRank.EIGHT,CardSuit.CLUBS);
	cardArrayList.add(pc8);
	
	PokerCard pc9 = new PokerCard(CardRank.NINE,CardSuit.CLUBS);
	cardArrayList.add(pc9);
	
	PokerCard pc10 = new PokerCard(CardRank.TEN,CardSuit.CLUBS);
	cardArrayList.add(pc10);
	
	PokerCard pc11 = new PokerCard(CardRank.JACK,CardSuit.CLUBS);
	cardArrayList.add(pc11);
	
	PokerCard pc12 = new PokerCard(CardRank.QUEEN,CardSuit.CLUBS);
	cardArrayList.add(pc12);
	
	PokerCard pc13 = new PokerCard(CardRank.KING,CardSuit.CLUBS);
	cardArrayList.add(pc13);
	
	
	PokerCard pd1 = new PokerCard(CardRank.ACE,CardSuit.DIAMONDS);
	cardArrayList.add(pd1);
	
	PokerCard pd2 = new PokerCard(CardRank.TWO,CardSuit.DIAMONDS);
	cardArrayList.add(pd2);
	
	PokerCard pd3 = new PokerCard(CardRank.THREE,CardSuit.DIAMONDS);
	cardArrayList.add(pd3);
	
	PokerCard pd4 = new PokerCard(CardRank.FOUR,CardSuit.DIAMONDS);
	cardArrayList.add(pd4);
	
	PokerCard pd5 = new PokerCard(CardRank.FIVE,CardSuit.DIAMONDS);
	cardArrayList.add(pd5);
	
	PokerCard pd6 = new PokerCard(CardRank.SIX,CardSuit.DIAMONDS);
	cardArrayList.add(pd6);
	
	PokerCard pd7 = new PokerCard(CardRank.SEVEN,CardSuit.DIAMONDS);
	cardArrayList.add(pd7);
	
	PokerCard pd8 = new PokerCard(CardRank.EIGHT,CardSuit.DIAMONDS);
	cardArrayList.add(pd8);
	
	PokerCard pd9 = new PokerCard(CardRank.NINE,CardSuit.DIAMONDS);
	cardArrayList.add(pd9);
	
	PokerCard pd10 = new PokerCard(CardRank.TEN,CardSuit.DIAMONDS);
	cardArrayList.add(pd10);
	
	PokerCard pd11 = new PokerCard(CardRank.JACK,CardSuit.DIAMONDS);
	cardArrayList.add(pd11);
	
	PokerCard pd12 = new PokerCard(CardRank.QUEEN,CardSuit.DIAMONDS);
	cardArrayList.add(pd12);
	
	PokerCard pd13 = new PokerCard(CardRank.KING,CardSuit.DIAMONDS);
	cardArrayList.add(pd13);
	
	
	PokerCard ph1 = new PokerCard(CardRank.ACE,CardSuit.HEARTS);
	cardArrayList.add(ph1);
	
	PokerCard ph2 = new PokerCard(CardRank.TWO,CardSuit.HEARTS);
	cardArrayList.add(ph2);
	
	PokerCard ph3 = new PokerCard(CardRank.THREE,CardSuit.HEARTS);
	cardArrayList.add(ph3);
	
	PokerCard ph4 = new PokerCard(CardRank.FOUR,CardSuit.HEARTS);
	cardArrayList.add(ph4);
	
	PokerCard ph5 = new PokerCard(CardRank.FIVE,CardSuit.HEARTS);
	cardArrayList.add(ph5);
	
	PokerCard ph6 = new PokerCard(CardRank.SIX,CardSuit.HEARTS);
	cardArrayList.add(ph6);
	
	PokerCard ph7 = new PokerCard(CardRank.SEVEN,CardSuit.HEARTS);
	cardArrayList.add(ph7);
	
	PokerCard ph8 = new PokerCard(CardRank.EIGHT,CardSuit.HEARTS);
	cardArrayList.add(ph8);
	
	PokerCard ph9 = new PokerCard(CardRank.NINE,CardSuit.HEARTS);
	cardArrayList.add(ph9);
	
	PokerCard ph10 = new PokerCard(CardRank.TEN,CardSuit.HEARTS);
	cardArrayList.add(ph10);
	
	PokerCard ph11 = new PokerCard(CardRank.JACK,CardSuit.HEARTS);
	cardArrayList.add(ph11);
	
	PokerCard ph12 = new PokerCard(CardRank.QUEEN,CardSuit.HEARTS);
	cardArrayList.add(ph12);
	
	PokerCard ph13 = new PokerCard(CardRank.KING,CardSuit.HEARTS);
	cardArrayList.add(ph13);
	
	
	
	PokerCard ps1 = new PokerCard(CardRank.ACE,CardSuit.SPADES);
	cardArrayList.add(ps1);
	
	PokerCard ps2 = new PokerCard(CardRank.TWO,CardSuit.SPADES);
	cardArrayList.add(ps2);
	
	PokerCard ps3 = new PokerCard(CardRank.THREE,CardSuit.SPADES);
	cardArrayList.add(ps3);
	
	PokerCard ps4 = new PokerCard(CardRank.FOUR,CardSuit.SPADES);
	cardArrayList.add(ps4);
	
	PokerCard ps5 = new PokerCard(CardRank.FIVE,CardSuit.SPADES);
	cardArrayList.add(ps5);
	
	PokerCard ps6 = new PokerCard(CardRank.SIX,CardSuit.SPADES);
	cardArrayList.add(ps6);
	
	PokerCard ps7 = new PokerCard(CardRank.SEVEN,CardSuit.SPADES);
	cardArrayList.add(ps7);
	
	PokerCard ps8 = new PokerCard(CardRank.EIGHT,CardSuit.SPADES);
	cardArrayList.add(ps8);
	
	PokerCard ps9 = new PokerCard(CardRank.NINE,CardSuit.SPADES);
	cardArrayList.add(ps9);
	
	PokerCard ps10 = new PokerCard(CardRank.TEN,CardSuit.SPADES);
	cardArrayList.add(ps10);
	
	PokerCard ps11 = new PokerCard(CardRank.JACK,CardSuit.SPADES);
	cardArrayList.add(ps11);
	
	PokerCard ps12 = new PokerCard(CardRank.QUEEN,CardSuit.SPADES);
	cardArrayList.add(ps12);
	
	PokerCard ps13 = new PokerCard(CardRank.KING,CardSuit.SPADES);
	cardArrayList.add(ps13);
	
		
	
		
		return cardArrayList;
	
	}







			
		
		
	}
	
	


