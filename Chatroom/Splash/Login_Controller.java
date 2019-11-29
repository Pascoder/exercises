package Splash;



import javafx.event.Event;

public class Login_Controller {
	private Login_Model model;
	private Login_View view;
	private final JavaFX_App_Template template;
	
	
	public Login_Controller(Login_Model model, Login_View view, final JavaFX_App_Template javaFX_App_Template) {
		this.model = model;
		this.view = view;
		this.template = javaFX_App_Template;
		
		
		view.btnlogin.setOnAction(this::clickLogin);
	
		
	
	}
	
	
	public void clickLogin(Event e) {
		String [] newaccount = new String[2];
		newaccount[0] = view.txtusername.getText();
		newaccount[1] = view.txtpassword.getText();
		
		model.createAccount(newaccount[0], newaccount[1]);
		
		template.startApp();
		
		
	}

}
