package Splash;

import java.util.ArrayList;

public class Message {
	static ArrayList <String> messages = new ArrayList<String>();
	
	String message = null;
	static int ID = -1;
	


public static int addnewMessage(String msg) {
	 	ID++;
		messages.add(msg);
		System.out.println(msg);
		return ID;
		
	}
public static String getMessage(int id) {
return messages.get(id);


}

}
