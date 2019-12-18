package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.event.Event;

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
	
		
	
	}
	
	//@TODO Fehler nach dem erstellen eines Loggins kann nicht direkt dieser Button gerdrï¿½ckt werden, programm muss zuerst geschlossen werden??
	public void clickLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();// changed to pw TextField
		String servermessage = null;
			
		try {
		
		if(username.length() <3 || password.length() <3) {
			view.status.setText("Please enter password and Username with more then 3 character");	
		}else {
			//Pruefen ob das Login auf dem Server existiert
			
				String senden = "Login|"+username+"|"+password;
				
				servicelocator.getConfiguration().getWriter().write(senden);
				servicelocator.getConfiguration().getWriter().write("\n");
				servicelocator.getConfiguration().getWriter().flush();
				
				
				
				 Integer i = 0;
                 while (i <= 20000000) {//<--muss mit einer Property ersetzt werden
                     i++;
                 }

               
              
				
			if(servicelocator.getConfiguration().getCorrectLogin()==true) {
				
				servicelocator.getLogger().info("Login succsesfull");
			
				template.startApp();
				
				view.stage.close();
				//Hier wird AccountCreated auf true gesetzt nun kann in Sort Message (Configurator) keine Nachricht mehr für den createaccount empfangen werden
				servicelocator.getConfiguration().setAccountCreated(true);
				
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
			//Hier werden DAten fuer das Login an Server geschickt
			try{
			//Senden einer neuen Loggin Datei  an Server
			String senden = "CreateLogin|"+username+"|"+password;
			
			servicelocator.getConfiguration().getWriter().write(senden);
			servicelocator.getConfiguration().getWriter().write("\n");
			servicelocator.getConfiguration().getWriter().flush();
			
			
			 Integer i = 0;
             while (i <= 20000000) {//<--muss mit einer Property ersetzt werden
                 i++;
             }
             
             
			boolean servermessage = servicelocator.getConfiguration().accountCreated();
			if(servermessage==true) {
			servicelocator.getLogger().info("createLogin succsessfull");
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
