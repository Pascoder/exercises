package first;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FileCopyView {
	private final FileCopyModel model;
	private final Stage stage;
	
	// GUI controls
	Label lblInstructions = new Label("Enter complete paths for the input and output files");
	Label lblInput = new Label("Input file");
	TextField txtInput = new TextField();
	Label lblOutput = new Label("Output file");
	TextField txtOutput = new TextField();
	Button btnCopy = new Button("Copy");
	
	public FileCopyView(Stage stage, FileCopyModel model) {
		this.stage = stage;
		this.model = model;
		
		GridPane root = new GridPane();
		root.add(lblInstructions,  0,  0, 2, 1);
		root.add(lblInput, 0, 1);
		root.add(txtInput, 1, 1);
		root.add(lblOutput, 0, 2);
		root.add(txtOutput, 1, 2);
		root.add(btnCopy, 0, 3, 2, 1);
		
		// Button has large max-width; tell GridPane to expand itButton has large max-size
		btnCopy.setMaxWidth(10000);
		GridPane.setFillWidth(btnCopy,  true);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("File copy");
	}
	
	public void start() {
		stage.show();
	}
	
}
