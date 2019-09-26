package Poker;

public class PokerController {
private PokerModel model;
private PokerView view;

	
public PokerController(PokerModel model, PokerView view) {
	this.model = model;
	this.view = view;
	//Shuffle method
		view.shuffle.setOnAction((event)-> {
		//model.shuffle(view.imageArray);//<-- method shuffle change the position of the images in the array imageArray
		System.out.println("works");
	});
	//Deal method
		view.deal.setOnAction((event)->{
		System.out.println("works");//Test if the button works
	});
	}
	
}

