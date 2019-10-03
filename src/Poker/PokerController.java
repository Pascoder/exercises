package Poker;



public class PokerController {

private PokerModel model;

private PokerView view;



	

public PokerController(PokerModel model, PokerView view) {

	this.model = model;

	this.view = view;

	//Shuffle method

		view.shuffle.setOnAction((event)-> {

		view.deck.shuffleDeck(view.deck);
		view.deck.printDeck(view.deck); //Test ob gemischelt wird

		

	});

	//Deal method

		view.deal.setOnAction((event)->{

		System.out.println("works");//Test if the button works

	});

	}

	

}