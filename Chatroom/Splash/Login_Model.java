package Splash;
import chatroom.server.Account;
public class Login_Model {
	
	protected Account account;

	public String createAccount(String username, String password) {
		boolean succsesfull = false;
		String created = null;
		
		this.account = new Account(username, password);
		if(account != null) {
			succsesfull = true;
		}
		
		
		if(succsesfull == true) {
			created = "Account created";
		}else {
			created = "not succsesfull";
		}
			
		
		return created;
		
	}

}
