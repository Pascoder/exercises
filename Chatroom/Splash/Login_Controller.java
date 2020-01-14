package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Login_Controller {
	private Login_Model model;
	private Login_View view;
	private final JavaFX_App_Template template;
	private ServiceLocator servicelocator;
	private  String salt;
	
	
	
	
	public Login_Controller(Login_Model model, Login_View view, final JavaFX_App_Template javaFX_App_Template) {
		this.model = model;
		this.view = view;
		this.template = javaFX_App_Template;
		
		servicelocator = ServiceLocator.getServiceLocator();
		
		view.btnlogin.setOnAction(this::clickLogin);
		view.btnerstellen.setOnAction(this::createLogin);
		view.btnerstellen.disableProperty().bind(view.txtusername.textProperty().isEmpty());
		view.btnerstellen.disableProperty().bind(view.pwpassword.textProperty().isEmpty());
		view.btnlogin.disableProperty().bind(view.txtusername.textProperty().isEmpty());
		view.btnlogin.disableProperty().bind(view.pwpassword.textProperty().isEmpty());
		
	
	}
	
	
	public void clickLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();// changed to pw TextField
		String servermessage = null;
		
		
			
		try {
		
		if(username.length() <3 || password.length() <3) {
			view.status.setText("Password or Username to short (at least 3 letters)");	
		}else {
			//Check if the login exists on the server
			
				String senden = "Login|"+username+"|"+password;
				
				model.send(senden);
				
				servicelocator.getConfiguration().setAcutalUser(username);

               
              
				
			if(servicelocator.getConfiguration().getCorrectLogin()==true) {
				
				servicelocator.getLogger().info("Login succsesfull");
			
				template.startApp();
				
				view.stage.close();
				//Here AccountCreated is set to true - now no more messages for the createaccount can be received in Sort Message (Configurator)
				servicelocator.getConfiguration().setAccountCreated(true);
				
			}else {
				
				view.status.setText("wrong password or Username");
			}
		}
		}catch (Exception ex) {
			
			view.status.setText("Account don't exist");
		}
		view.txtusername.clear();
		view.pwpassword.clear();
	}
	
	
	
	
	//Create Login
	
	public void createLogin(Event e) {
		view.status.setText("");
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();
		
		
		if(username.length() < 3 || password.length() < 3) {
			view.status.setText("Password or Username to short (min 3 letters)");
			
		}
		else {
			//Here you can send data for the login to the server
			try{
			//Sending a new loggin file to server
			String senden = "CreateLogin|"+username+"|"+password;
			model.send(senden);
			
			
             
             
			boolean servermessage = servicelocator.getConfiguration().accountCreated();
			if(servermessage==true) {
			servicelocator.getLogger().info("createLogin succsessfull");
			view.status.setText("Created");
			}else {
			servicelocator.getLogger().info("login not created");
			
			}
			
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
