package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Chatraum {
	private String name;
	private Button btn;
	ObservableList <String> chatRoomList;
	
	
	public Chatraum(String name) {
		this.name = name;
		chatRoomList = FXCollections.observableArrayList();
		btn = new Button(name);
		
		
	}
	
	
	
	public void addChatMessage(String Message) {
		chatRoomList.add(Message);
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ObservableList<String> getChatRoomList() {
		return chatRoomList;
	}


	public void setChatRoomList(ObservableList<String> chatRoomList) {
		this.chatRoomList = chatRoomList;
	}


	public Button getBtn() {
		return btn;
	}


	public void setBtn(Button btn) {
		this.btn = btn;
	}
	
	
	
}
