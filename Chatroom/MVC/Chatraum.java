package MVC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
		lbl = new Label("-");
		
		
		
	}
	
	
	
	public void addChatMessage(String Message) {
	
		msgcounter++;
	
		messageList.add(Message);
		lbl.setText(msgcounter+"");
		
		
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


	
	
	
	
	
}
