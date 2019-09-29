package Poker;



public class CardDeck {
	
	private static PokerCard [] cardDeck;

	
	public CardDeck() {
		CardDeck.deckGenerator();
	}
	
	
	
	public static PokerCard[] deckGenerator() {
		
		int cardNumber = 1;
		final int TOTAL_CARDS = 60;
		cardDeck = new PokerCard [TOTAL_CARDS];
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Clubs");
			
				PokerCard pc = new PokerCard(s,i);
				cardDeck[cardNumber] = pc;
				cardNumber ++;
//				System.out.println(pc);
				
		}
	
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Hearts");
			
				PokerCard pc = new PokerCard(s,i);
				cardDeck[cardNumber] = pc;
				cardNumber ++;
//				System.out.println(pc);

		}
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Spades");
			
				PokerCard pc = new PokerCard(s,i);
				cardDeck[cardNumber] = pc;
				cardNumber ++;
//				System.out.println(pc);

		}
		
		for(int i = 1;i<=13;i++) {
			
			String s = new String ("Diamonds");
			
				PokerCard pc = new PokerCard(s,i);
				cardDeck[cardNumber] = pc;
				cardNumber ++;
//				System.out.println(pc);

		}
	
		return cardDeck;
		
	
	}



	@Override
	public String toString() {
		for(int i=0; i<this.cardDeck.length;i++) {
			
		}
		return;
	}
	
	

}
