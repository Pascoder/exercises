package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
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
	
	private static int counter = 0;
	public Button addplayer = new Button("Add Player");
	public Button enter = new Button("Start Game");
	public TextField t1 = new TextField();
	private static Label name = new Label("Name of Player "+(counter+1)+": ");
	static Stage startmenuStage = new Stage();
	private ArrayList<String> playersname = new ArrayList();
	
	
	
	
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
		name.setText("Name of Player "+(counter+1)+": ");
		this.playersname.add(t1.getText());
	}
	
}

public void StartGame(Event a) {
	
	
	startmenuStage.close();	
	
}
public int getCounter() {
	return this.counter;
}

}


