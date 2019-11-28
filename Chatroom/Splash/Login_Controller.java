package Splash;



import javafx.event.Event;

public class Login_Controller {
	private Login_Model model;
	private Login_View view;
	private JavaFX_App_Template javaFX_App_Template;
	
	public Login_Controller(Login_Model model, Login_View view) {
		this.model = model;
		this.view = view;
		
		
		
		view.btnlogin.setOnAction(this::clickLogin);
	
		
	
	}
	
	
	public void clickLogin(Event e) {
		
		
		
	}

}
