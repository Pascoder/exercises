package Poker;

import java.lang.reflect.Array;

public class PokerController {

private PokerModel model;

private PokerView view;

private CardDeck deck;

private PokerPlayer player;

private int playernumber = 0;




public PokerController(PokerModel model, PokerView view) {

	this.model = model;

	this.view = view;
	
	//Create CardDeck
	CardDeck deck = new CardDeck();
	deck.shuffleDeck(deck);
	deck.printDeck(deck); //Test ob deck erzeugt wird
	
	//Player
	this.player = new PokerPlayer("BeispielNamen");
	this.playernumber++; //muss am Anfang des Spieles gefragt werden wie viele Spieler
	
	//PokerPlayer wird ganzes Deck übergeben und daraus Hands generiert
	player.generateHand(deck);
	
	model.getHandType(player);
	
	
	
	//!!nachher muss hier in PokerPlayer klasse handtype zuweisen
	
	//Shuffle method

		view.shuffle.setOnAction((event)-> {

		deck.shuffleDeck(deck);
		deck.printDeck(deck);

		

	});

	//Deal method

		view.deal.setOnAction((event)->{

		System.out.println("works");//Test if the button works

	});
		
	}




	

}