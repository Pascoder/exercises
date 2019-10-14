package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;

public class EnterMenu{
	
	private int counter = 0;
	Button addplayer = new Button("Add Player");
	Button enter = new Button("Start Game");
	TextField t1 = new TextField();
	private static Label name = new Label("Name of Player "+PokerGame.NUM_PLAYERS+": ");
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
	/*startmenuScene.getStylesheets().add(
            getClass().getResource("poker.css").toExternalForm()); //CSS File muss noch ersetzt werden */
	startmenuStage.setScene(startmenuScene);
	
	startmenuStage.show();
	
	
	this.addplayer.setOnAction(this::addPlayer);
	this.enter.setOnAction(this::StartGame);
}



private void addPlayer(Event e) {
	if(counter <=5) {
		counter ++;
		name.setText("Name of Player "+counter+": ");
		String nameumwandeln = t1.getText();
		System.out.println(playersname);
		//this.playersname.add(nameumwandeln);
		
	}
	

	
}




public void StartGame(Event a) {
	
	System.out.println(this.counter);
	PokerGame.NUM_PLAYERS = this.counter;
	System.out.println(PokerGame.NUM_PLAYERS);
	
	startmenuStage.close();
	
	gamestarten =true;
	

	
	
}
}


