package first;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.Alert;

public class FileCopyModel {

	public FileCopyModel() {
	}

	public void FileCopy(String fileNameIn, String fileNameOut) {
		// Must enter full, correct paths
		File fileIn = new File(fileNameIn);
		File fileOut = new File(fileNameOut);

		if (!fileIn.exists() || fileIn.isDirectory() || !fileIn.canRead()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with input file");
			alert.showAndWait();
		} else if ((fileOut.isDirectory() || !fileIn.canWrite())) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with output file");
			alert.showAndWait();
		} else {		
			try (	FileInputStream streamIn = new FileInputStream(fileIn);
					FileOutputStream streamOut = new FileOutputStream(fileOut);) {
				byte[] buffer = new byte[4096];
				int bytesRead;
				// In a loop: read, write, read, write - until done
				bytesRead = streamIn.read(buffer); // returns actual number of bytes
				while (bytesRead != -1) {
					streamOut.write(buffer, 0, bytesRead); // buffer, begin, length
					bytesRead = streamIn.read(buffer);
				}
				streamOut.flush(); // Ensure all data has actually been written
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
				alert.showAndWait();
			}
		}
	}
}
