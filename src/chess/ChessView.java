package chess;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessView {
	private ChessModel model;
	private Stage stage;
	private Button A1,A2,A3;
	
	
	
public ChessView(Stage stage, ChessModel model) {
	this.model = model;
	this.stage = stage;
	stage.setTitle("Chess Game");
	
	GridPane root = new GridPane();
	
	A1=new Button("black");
	
	root.add(A1, 1, 1);
	
	Scene scene = new Scene (root,600,600);
	stage.setScene(scene);
	
	
	
}

	
	
	
		
		

	
	public void start() {
		stage.show();
		
		
		
		
		}
	
	

}
