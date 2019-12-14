package first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ExServer {

	
		
	
		private static Integer port = 50001;
		

		public static void main(String[] args) {
			try (ServerSocket listener = new ServerSocket(port, 10, null)) {
				
				while (true) {
				// Wait for request, then create input/output streams to talk to the client
				try (Socket client = listener.accept();
						BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
						PrintWriter out = new PrintWriter(client.getOutputStream());
				) {
				

					
					
					}
				}
			} catch (Exception e) {
				System.err.println(e);
				}
		};

}
