package webBrowser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.concurrent.Task;

/**
 * Model is a thread, so that it can work without blocking the GUI
 */
public class Model extends Thread {
	private Integer port;
	private final Logger logger = Logger.getLogger("");

	@Override
	public void run() {
		try (ServerSocket listener = new ServerSocket(port, 10, null)) {
			logger.info("Listening on port " + port);

			while (true) {
				// Wait for request, then create input/output streams to talk to the client
				try (Socket client = listener.accept();
					 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					 PrintWriter out = new PrintWriter(client.getOutputStream());
					) {

					logger.info("Request from client " + client.getInetAddress().toString() + " for server "
							+ client.getLocalAddress().toString());

					// Send our reply using HTTP 1.0
					// HTTP specifies \r\n line endings, though most programs don't care
					out.print("HTTP/1.0 200 \r\n"); // Version and status
					out.print("Content-Type: text/plain\r\n");
					out.print("\r\n"); // Required empty line

					// Read request from client and send it straight back
					// An empty string (length 0) is the end of an HTTP request
					StringBuilder received = new StringBuilder();
					String inString;
					while ((inString = in.readLine()) != null && inString.length() != 0) {
						received.append(inString + "\n");
					}
					
					// Send request back as our output
					String outString = received.toString();
					out.print(outString);
					logger.info("Request contents:\n" + outString);

					out.flush(); // Be safe, always "flush"
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	};

	public void setPort(Integer port) {
		this.port = port;
	}
}
