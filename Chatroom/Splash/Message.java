package Splash;

import java.util.ArrayList;

public class Message {
	static ArrayList <String> messages = new ArrayList<String>();
	
	
	


public static void addnewMessage(String msg) {
		messages.add(msg);
		System.out.println(msg);
		
	}

}
