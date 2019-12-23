package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Chatraum {
	private int newmessages = 0;
	private String name;
	private Button btn;
	ObservableList <String> messageList;
	
	
	public Chatraum(String name) {
		this.name = name;
		messageList = FXCollections.observableArrayList();
		btn = new Button(name);
		
		
	}
	
	
	
	public void addChatMessage(String Message) {
		this.newmessages++;
		messageList.add(Message);
		
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
	public int getnewMessageCounter() {
		return this.newmessages;
	}
	public void clearnewMessageCounter() {
		this.newmessages = 0;
	}
	
	
	
}
