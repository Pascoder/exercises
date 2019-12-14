package first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class ExClient {
	
	private static Integer port = 50001;
	

	public static void main(String[] args) {
		
		
		try(Socket clientSocket = new Socket("127.0.0.1", 50001);
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
						
				){
			
			outputWriter.write( "Was isch los?!");
			outputWriter.write("\n");
			outputWriter.flush();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
	
	

