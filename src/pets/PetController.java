package pets;


import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pets.Pet.Gender;
import pets.Pet.Species;

public class PetController {
	
	private PetView view;
	private PetModel model;

	public PetController(PetModel model, PetView view) {
		this.model = model;
		this.view = view;
		
		view.btnSave.setOnAction(this::save);
		view.btnDelete.setOnAction(this::delete);
		
		view.btnNext.setOnAction(this::next);
		view.btnPrevious.setOnAction(this::previous);
		
		view.btnDelete.disableProperty().bind(model.petProperty().isNull());
		view.btnSave.disableProperty().bind(view.txtName.textProperty().isEmpty());
	}
	
	private void save(ActionEvent e) {
		Species species = view.cmbSpecies.getValue();
		Gender gender = view.cmbGender.getValue();
		String name = view.txtName.getText();
		String musicFile = "shuffle.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mp = new MediaPlayer(sound);
		mp.play();
		if (species != null && gender != null && name != null && name.length() > 0) {
			model.savePet(species, gender, name);
			updateView(model.getPet());
			updateList(model.getPet());
		}
	}
	
	private void updateList(Pet pet) {
		if (pet != null) {
			view.lblDataID2.setText(Integer.toString(pet.getID()));
			view.lblDataName2.setText(pet.getName());
			view.lblDataSpecies2.setText(pet.getSpecies().toString());
			view.lblDataGender2.setText(pet.getGender().toString());
		} else {
			view.lblDataID2.setText("");
			view.lblDataName2.setText("");
			view.lblDataSpecies2.setText("");
			view.lblDataGender2.setText("");
		}
	}
	
	private void next(ActionEvent e) {
		updateList(model.getPets(0));
		
	}
	
	private void previous(ActionEvent e) {
		updateList(model.getPets(1));
	
	
	}
	
	private void delete(ActionEvent e) {
		model.deletePet();
		updateView(model.getPet());
	}
	
	private void updateView(Pet pet) {
		if (pet != null) {
			view.lblDataID.setText(Integer.toString(pet.getID()));
			view.lblDataName.setText(pet.getName());
			view.lblDataSpecies.setText(pet.getSpecies().toString());
			view.lblDataGender.setText(pet.getGender().toString());
		} else {
			view.lblDataID.setText("");
			view.lblDataName.setText("");
			view.lblDataSpecies.setText("");
			view.lblDataGender.setText("");
		}
	}
}



