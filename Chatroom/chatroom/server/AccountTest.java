package chatroom.server;

public class AccountTest {

	public static void main(String[] args) {
		Account account = new Account("Frank", "123");
		Account account3 = new Account("Dave","234");
		account.add(account);
		account.add(account3);
		Account account2 = account.exists("Mitch");
		System.out.println(account2.getUsername());
		
	}

}
