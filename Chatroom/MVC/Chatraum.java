package MVC;

import java.io.File;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class Chatraum {

	private String name;
	private Button btn;
	private Label lbl;
	ObservableList <String> messageList;
	
	

	private int msgcounter = 0;
	
	
	public Chatraum(String name) {
		this.name = name;
		messageList = FXCollections.observableArrayList();
		btn = new Button(name);
		
		lbl = new Label();
		lbl.setFont(new Font(35));
		lbl.setStyle("-fx-text-fill: green");
		lbl.setStyle("-fx-font-weight: bold");
	
		
	}
	
	
	
	public void addChatMessage(String Message, String actualUser) {
		messageList.add(Message);
		if(!Message.contains(actualUser+": ")){
			playSound();
		}
		
		

		
	}


	private void playSound() {
		//Play sound when receiving a new Message
				String musicFile = "WhatsApp.mp3";
				
				try {Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();} catch(Exception e) {
						e.getMessage();
				}		
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ObservableList<String> getChatRoomList() {
		return messageList;
	}


	public void setChatRoomList(ObservableList<String> messageList) {
		this.messageList = messageList;
	}


	public Button getBtn() {
		return btn;
	}


	public void setBtn(Button btn) {
		this.btn = btn;
	}
	
	
	
	public Label getLabel() {
		return this.lbl;
	}
	
	public void setMsgCounter() {
		this.msgcounter = 0;
	}
	public int getMsgCounter() {
		return this.msgcounter;
	}
	public void increaseCounter() {
		this.msgcounter = msgcounter+1;
	}


	
	
	
	
	
}
