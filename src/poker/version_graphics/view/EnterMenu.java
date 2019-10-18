package poker.version_graphics.view;

import java.util.ArrayList;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;

public class EnterMenu{
	
	private static int counter = 0;
	public Button addplayer = new Button("Add Player");
	public Button enter = new Button("Start Game");
	public TextField t1 = new TextField();
	private static Label name = new Label("	Name of Player "+(counter+1)+": ");
	static Stage startmenuStage = new Stage();
	public static ArrayList<String> playersname = new ArrayList<String>();
	
	private final SimpleObjectProperty<String> starter = new SimpleObjectProperty<>();
	
	public EnterMenu() {
	
	
	addplayer.disableProperty().bind(t1.textProperty().isEmpty());
	
	
	

	
   
	GridPane entergrid = new GridPane();

	
	entergrid.add(name, 0, 0);
	entergrid.add(t1, 1, 0);
	
	entergrid.add(addplayer, 1, 1);
	entergrid.add(enter, 2, 1);
	
	Scene startmenuScene = new Scene(entergrid,900,400);
	startmenuScene.getStylesheets().add(
            getClass().getResource("PokerStyle.css").toExternalForm()); 
	startmenuStage.setScene(startmenuScene);
	startmenuStage.setTitle("Player Selection");
	startmenuStage.show();
	
	
	this.addplayer.setOnAction(this::addPlayer);
	this.enter.setOnAction(this::StartGame);
	
}



private void addPlayer(Event e) {
	if(counter <=5) {
		
		counter ++;
		name.setText("	Name of Player "+(counter+1)+": ");
		this.playersname.add(t1.getText());
		t1.clear();
	}
	
}

public void StartGame(Event a) {
	
	
	startmenuStage.close();	
	
}
public int getCounter() {
	return this.counter;
}
public ArrayList<String> getPlayer() {
	return this.playersname;
	
	
}
public void setProperty() {
	starter.set("start");
}

}


