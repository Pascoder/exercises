package chess;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessView {
	private ChessModel model;
	private Stage stage;
	
	
protected ChessView(Stage stage, ChessModel model) {
	this.model = model;
	this.stage = stage;
	stage.setTitle("Chess Game");
	
	GridPane root = new GridPane();
	
	Scene scene = new Scene (root,1000,500);
	
	
	
}

	
	
	
		
		

	
	public void start() {
		stage.show();
		
		
		
		
		}
	
	

}
