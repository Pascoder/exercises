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
	PokerPlayer player = new PokerPlayer(this.playernumber);
	this.playernumber++; //muss am Anfang des Spieles gefragt werden wie viele Spieler
	
	//PokerPlayer wird ganzes Deck uebergeben und daraus Hands generiert
	player.generateHand(deck);
	
	//Shuffle method

		view.shuffle.setOnAction((event)-> {

		

		

	});

	//Deal method

		view.deal.setOnAction((event)->{

		System.out.println("works");//Test if the button works

	});
		
	}




	

}