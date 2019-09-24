package chess;

import javafx.application.Application;
import javafx.stage.Stage;

public class ChessMVC extends Application {
	private ChessView view;
	private ChessController controller;
	private ChessModel model;
	

	public static void main(String[] args) {
		launch(args);
		
	}
	
	

	public void start(Stage stage)  {
		ChessView view = new ChessView(stage, model);
//		ChessController controller = new ChessController(view,model);
		view.start();
		
		}
	
	

}
