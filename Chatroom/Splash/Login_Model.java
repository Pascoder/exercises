package Splash;
import chatroom.server.Account;
public class Login_Model {
	
	protected Account account;

	public String createAccount(String username, String password) {
		
		boolean succsesfull = false;
		String created = null;
		
		this.account = new Account(username, password);
		this.account.add(account);
		//Test ob nun der Account in der ArrayListe für Accounts ist
		
		Account check = account.exists(username);
		
		
		if(check.getUsername() == username) {
			created = "Account created";
		}else {
			created = "not succsesfull";
		}
			
		
		return created;
		
	}
	
	

	

}
