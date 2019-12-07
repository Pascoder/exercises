package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.sun.media.jfxmedia.logging.Logger;


import javafx.event.Event;

public class Login_Controller {
	private Login_Model model;
	private Login_View view;
	private final JavaFX_App_Template template;
	private Socket socket;
	private ServiceLocator servicelocator;
	private  String salt;
	
	
	public Login_Controller(Login_Model model, Login_View view, final JavaFX_App_Template javaFX_App_Template, Socket socket) {
		this.model = model;
		this.view = view;
		this.template = javaFX_App_Template;
		this.socket = socket; //Socket aus Start Klasse an Controller übergeben
		
		
		view.btnlogin.setOnAction(this::clickLogin);
		view.btnerstellen.setOnAction(this::createLogin);
	
		
	
	}
	
	//@TODO Fehler nach dem erstellen eines Loggins kann nicht direkt dieser Button gerdrückt werden, programm muss zuerst geschlossen werden??
	public void clickLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();// changed to pw TextField
		String servermessage = null;
			
		try {
		
		if(username.length() <3 || password.length() <3) {
			view.status.setText("Please enter password and Username with more then 3 character");	
		}else {
			//Prüfen ob das Login auf dem Server existiert
			try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				){
				//Senden einer neuen Loggin Datei  an Server
				String senden = "Login|"+username+"|"+password;
				writer.write(senden);
				writer.write("\n");
				writer.flush();
				
				//Empfangen der Antwort des Servers
				servermessage = reader.readLine();
				this.salt = servermessage.substring(12,44);
				view.status.setText(servermessage);
			
				
				}catch(Exception exeption) {
					this.servicelocator.getLogger().info("Something goes wrong by Login");
				}
			String ok = "true";
			if(servermessage.substring(7,11).equals(ok)) {
				template.startApp(this.salt);
				view.stage.close();
			}else {
				
				view.status.setText("wrong password or Username");
			}
		}
		}catch (Exception ex) {
			view.status.setText("Account don't exist");
		}
	}
	
	
	
	
	//Create Login
	
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
			//Hier werden DAten für das Login an Server geschickt
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
				this.servicelocator.getLogger().info("Something goes wrong by Creating your login");
			}
		
			view.txtusername.clear();
			view.pwpassword.clear();
		}
		
		
	}
	
	public String getSalt() {
		return this.salt;
	}
	

}
