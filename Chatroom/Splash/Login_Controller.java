package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import chatroom.server.Account;
import javafx.event.Event;

public class Login_Controller {
	private Login_Model model;
	private Login_View view;
	private final JavaFX_App_Template template;
	private Socket socket;
	
	
	public Login_Controller(Login_Model model, Login_View view, final JavaFX_App_Template javaFX_App_Template, Socket socket) {
		this.model = model;
		this.view = view;
		this.template = javaFX_App_Template;
		this.socket = socket; //Socket aus Start Klasse an Controller übergeben
		
		
		view.btnlogin.setOnAction(this::clickLogin);
		view.btnerstellen.setOnAction(this::createLogin);
		
	
	}
	
	
	public void clickLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();// changed to pw TextField
		
	
			
		try {
		
		if(username.isEmpty() || password.isEmpty()) {
			view.status.setText("Please enter password and Username");	
		}else {
			Account check = Account.exists(username);
			if(check.checkPassword(password) == true) {
				template.startApp();
				view.stage.close();
			}else {
				
				view.status.setText("wrong password or Username");
			}
		}
		}catch (Exception ex) {
			view.status.setText("Account don't exist");
		}
		
		
	}
	
	public void createLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();
		
		
		if(username.isEmpty() || password.isEmpty()) {
			view.status.setText("Please try again");
		}if(username.length() < 3 || password.length() < 3) {
			view.status.setText("Password and username must have at least 3 characters");
			
		}
		else {
			
			//Hier werden DAten für den Account an Server geschickt
			try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			){
			//Senden einer neuen Loggin Datei  an Server
			String senden = "CreateLogin|"+username+"|"+password;
			writer.write(senden);
			writer.write("\n");
			writer.flush();
			
			//Empfangen der Antwort des Servers
			String servermessage = reader.readLine();
			view.status.setText(servermessage);
			
			}catch(Exception exeption) {
				exeption.printStackTrace();
			}
		
			view.txtusername.clear();
			view.pwpassword.clear();
		}
		
		
	}

}
