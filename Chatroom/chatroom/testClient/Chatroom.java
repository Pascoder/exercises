package chatroom.testClient;



import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.io.Serializable;

import java.time.Instant;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.TreeSet;

import java.util.logging.Logger;






/**

 * This class represents a chatroom, which may be either public or private, and

 * has a list of accounts that are members. A chatroom is "owned" by the user

 * who created it. If that user account is deleted, the chatroom can be claimed

 * by the next person to create an account with the same name.

 * 

 * To avoid problems with duplicates, we use a TreeSet for the membership list.

 * We store usernames rather than account object, to make loading/saving data

 * simpler

 * 

 * At the class level, we maintain a list of all existing chatrooms.

 */

public class Chatroom implements Comparable<Chatroom>,  Serializable {

	private static final long serialVersionUID = 1;



	private static Logger logger = Logger.getLogger("");



	private static final TreeSet<Chatroom> chatrooms = new TreeSet<>();



	private final String name;

	private final String owner; // username of an account

	private final boolean isPublic;

	private final ArrayList<String> users = new ArrayList<>();

	private Instant lastMessage;



	/**

	 * Add a new chatroom to our list of chatrooms

	 */

	public static void add(Chatroom chatroom) {

		chatrooms.add(chatroom);

	}



	/**

	 * Remove a chatroom from our list of valid chatrooms

	 */

	public static void remove(Chatroom chatroom) {

		synchronized (chatrooms) {

			for (Iterator<Chatroom> i = chatrooms.iterator(); i.hasNext();) {

				if (chatroom == i.next()) i.remove();

			}

		}

	}

	

	/**

	 * List chatroom names

	 */

	public static ArrayList<String> listPublicNames() {

		ArrayList<String> names = new ArrayList<>();

		synchronized (chatrooms) {

			for (Chatroom c : chatrooms) if (c.isPublic) names.add(c.name);

		}

		return names;

	}

	

	/**

	 * Find and return an existing chatroom

	 */

	public static Chatroom exists(String name) {

		synchronized (chatrooms) {

			for (Chatroom chatroom : chatrooms) {

				if (chatroom.name.equals(name)) return chatroom;

			}

		}

		return null;

	}



	/**

	 * Clean up old chatrooms -- called by cleanup thread

	 */

	public static void cleanupChatrooms() {

		synchronized (chatrooms) {

			Instant expiry = Instant.now().minusSeconds(3 * 86400); // 3 days

			for (Iterator<Chatroom> i = chatrooms.iterator(); i.hasNext();) {

				Chatroom chatroom = i.next();

				if (chatroom.lastMessage.isBefore(expiry)) i.remove();

			}

		}

	}



	/**

	 * Save chatrooms to disk -- called by cleanup thread

	 */

	

	public Chatroom(String name, boolean isPublic, String owner) {

		this.name = name;

		this.isPublic = isPublic;

		this.owner = owner;

		this.lastMessage = Instant.now();

	}



	
	


	/**

	 * Send a message to every user of this chatroom who is logged on. Users may

	 * remain in a chatroom after logout or disconnection, so we remove inactive

	 * users when we find them.

	 * 

	 * Note: If multiple clients are logged in with the same name, only one will

	 * receive a message. An alternative would be to iterate through the

	 * clients-list and match names, but this would require synchronization on the

	 * clients list, and our I/O may be slow. One would also need to put the cleanup

	 * of invalid usernames into the Client cleanup code, rather than doing it here.

	 */



	




	public boolean equals(Object o) {

		if (o == null || o.getClass() != this.getClass()) return false;

		Chatroom oc = (Chatroom) o;

		return oc.name.equals(this.name);

	}



	@Override

	public int compareTo(Chatroom c) {

		return name.compareTo(c.name);

	}



	public String getOwner() {

		return owner;

	}



	public boolean isPublic() {

		return isPublic;

	}



	public void addUser(String username) {

		if (!users.contains(username)) users.add(username);

	}



	public void removeUser(String username) {

		users.remove(username);

	}

	

	public ArrayList<String> getUsers() {

		return users; // Arguably, we should return only a copy

	}

}