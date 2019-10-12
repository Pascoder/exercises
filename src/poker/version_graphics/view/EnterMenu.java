package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;

public class EnterMenu{
	
	public static int playercounter = 0;
	Button enter = new Button("Start Game");
	Button addplayer = new Button("Add Player");
	TextField t1 = new TextField();
	private static Label name = new Label("Name of Player "+playercounter+": ");
	static Stage startmenuStage = new Stage();
	private int players = 0;
	private ArrayList<String> playersname;
	public boolean gamestarten = false;
	
	
public EnterMenu() {
	
	 //kann nachher mit if schleife vor haupt scene gestellt werden, wenn Start Game gedrückt dann soll erst spiel angezeigt werden
   
	GridPane entergrid = new GridPane();
	
	
	
	
	
	entergrid.add(name, 0, 0);
	entergrid.add(t1, 1, 0);
	
	entergrid.add(addplayer, 1, 1);
	entergrid.add(enter, 2, 1);
	
	Scene startmenuScene = new Scene(entergrid);
	startmenuScene.getStylesheets().add(
            getClass().getResource("poker.css").toExternalForm()); //CSS File muss noch ersetzt werden 
	startmenuStage.setScene(startmenuScene);
	startmenuStage.show();
	
	enter.setOnAction(e-> StartGame());
	addplayer.setOnAction(e-> addPlayer());
}



private void addPlayer() {
	if(PokerGame.NUM_PLAYERS <=5) {
		PokerGame.NUM_PLAYERS++;
		name.setText("Name of Player "+PokerGame.NUM_PLAYERS+": ");
		
	
		this.playersname.add(t1.getText());
	}
	
	
}




private void StartGame() {
	// TODO Auto-generated method stub
	gamestarten = true;
	startmenuStage.close();

	
	
}
}
