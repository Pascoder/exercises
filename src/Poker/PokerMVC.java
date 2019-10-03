package Poker;



import javafx.application.Application;

import javafx.stage.Stage;



public class PokerMVC extends Application{

//Create instance of PokerView, PokerController and PokerModel to outsource the task's

	private PokerView view;

	private PokerController controller;

	private PokerModel model;

	

	public static void main(String[] args) {

		launch(args);



	}

	public void start(Stage Primary) {

		this.view = new PokerView(Primary,model);

		this.controller = new PokerController(model,view);

		this.model = new PokerModel();

		view.start();

		

	}



}