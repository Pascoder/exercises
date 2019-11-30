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
		view.btnerstellen.setOnAction(this::createAccount);
		
	
	}
	
	
	public void clickLogin(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();
		
		
		boolean succsesfull = model.account.checkPassword(password);
		
		if(succsesfull == true) {
			template.startApp();
			view.stage.close();
		}else {
			view.status.setText("wrong password");
		}
		
		
		
		
	}
	
	public void createAccount(Event e) {
		String username, password;
		username = view.txtusername.getText();
		password = view.pwpassword.getText();
		
		
		System.out.println(password);
		if(username.isEmpty() || password.isEmpty()) {
			view.status.setText("Please try again");
		}else {
			
			String succsesfull = model.createAccount(username, password);
			view.status.setText(succsesfull);
			view.txtusername.clear();
			view.pwpassword.clear();
		}
		
		
	}

}
