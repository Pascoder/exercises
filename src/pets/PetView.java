package pets;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pets.Pet.Gender;
import pets.Pet.Species;

public class PetView {
	
	private Stage stage;
	private PetModel model;
	
	// Controls used for data processing
	TextField txtName = new TextField();
	ComboBox<Species> cmbSpecies = new ComboBox<>();
	ComboBox<Gender> cmbGender = new ComboBox<>();
	Label lblDataID = new Label();
	Label lblDataName = new Label();
	Label lblDataSpecies = new Label();
	Label lblDataGender = new Label();
	// Show actual Data in the ListPane
	Label lblDataID2 = new Label();
	Label lblDataName2 = new Label();
	Label lblDataSpecies2 = new Label();
	Label lblDataGender2 = new Label();
	Label lblNumberOfPets = new Label();
	
	//Buttons
	Button btnNext = new Button("NEXT");
	Button btnPrevious = new Button("PREVIOUS");
	// Buttons
	Button btnSave = new Button("Save");
	Button btnDelete = new Button("Delete");

	public PetView(Stage stage, PetModel model) {
		this.stage = stage;
		this.model = model;
		
		VBox root = new VBox();
		root.getChildren().add(createDataEntryPane());
		root.getChildren().add(createControlPane());
		root.getChildren().add(createDataDisplayPane());
		root.getChildren().add(createListPane());

		// Standard stuff for Scene and Stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("Pet.css").toExternalForm());
		stage.setTitle("Enter and display a pet");
		stage.setScene(scene);
		stage.show();
	}
	
	public void start() {
		stage.show();
	}

	private Pane createDataEntryPane() {
		GridPane pane = new GridPane();
		pane.setId("dataEntry");
		// Declare the individual controls in the GUI
		Label lblName = new Label("Name");
		Label lblSpecies = new Label("Species");
		Label lblGender = new Label("Gender");
		
		// Fill combos
		cmbSpecies.getItems().addAll(Pet.Species.values());
		cmbSpecies.setValue(Species.CAT);
		cmbGender.getItems().addAll(Pet.Gender.values());
		cmbGender.setValue(Gender.FEMALE);
		
		// Organize the layout, add in the controls (col, row)
		pane.add(lblName, 0, 0);		pane.add(txtName, 1, 0);
		pane.add(lblSpecies, 0, 1);		pane.add(cmbSpecies, 1, 1);
		pane.add(lblGender, 0, 2);	pane.add(cmbGender, 1, 2);
		
		return pane;
	}

	private Pane createControlPane() {
		GridPane pane = new GridPane();
		pane.setId("controlArea");
		pane.add(btnSave, 0, 0);
		pane.add(btnDelete, 1, 0);
		
		return pane;
	}

	private Pane createDataDisplayPane() {
		GridPane pane = new GridPane();
		pane.setId("dataDisplay");
		// Declare the individual controls in the GUI
	    Label lblID = new Label("ID");
		Label lblName = new Label("Name");
		Label lblSpecies = new Label("Species");
		Label lblGender = new Label("Gender");
		
		// Organize the layout, add in the controls (col, row)
	    pane.add(lblID, 0, 0); pane.add(lblDataID, 1, 0);
	    pane.add(lblName, 0, 1); pane.add(lblDataName, 1, 1);
	    pane.add(lblSpecies, 0, 2); pane.add(lblDataSpecies, 1, 2);
	    pane.add(lblGender, 0, 3); pane.add(lblDataGender, 1, 3);
	    
	
	    return pane;
	}    
	    
	private Pane createListPane() {
		GridPane pane = new GridPane();
		pane.setId("petList");
	    Label lblID1 = new Label("ID   ");
		Label lblName1 = new Label("Name   ");
		Label lblSpecies1 = new Label("Species   ");
		Label lblGender1 = new Label("Gender   ");
		
		
		pane.add(lblID1, 0, 0); pane.add(lblDataID2, 0, 1);
	    pane.add(lblName1, 2, 0); pane.add(lblDataName2, 2, 1);
	    pane.add(lblSpecies1, 4, 0); pane.add(lblDataSpecies2, 4, 1);
	    pane.add(lblGender1, 6, 0); pane.add(lblDataGender2, 6, 1);
	    pane.add(btnPrevious, 10, 0);pane.add(btnNext, 10, 1);
	    pane.add(lblNumberOfPets, 10, 2);
	    	
	    

	    	
	    return pane;	
	    	
		
	}
	
}


