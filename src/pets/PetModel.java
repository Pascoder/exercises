package pets;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetModel {
	

		private final SimpleObjectProperty<Pet> petProperty = new SimpleObjectProperty<>();
		private final ObservableList<Pet> pets = FXCollections.observableArrayList();
		private int i = 0;
		
		
		public void savePet(Pet.Species species, Pet.Gender gender, String name) {
			petProperty.set(new Pet(species, gender, name));
			pets.add(new Pet(species, gender, name));
		}
		
		public void deletePet() {
			petProperty.set(null);
		}
		
		public Pet getPet() {
			return petProperty.get();
		}
		
		public SimpleObjectProperty<Pet> petProperty() {
			return petProperty;
		}
		
		public Pet getPets(int k) {
			if (k==0) {
				i++;
				return pets.get(i);
				
			} else {
				i--;
				return pets.get(i);
			}
				
		
		
				
			}
		}
		
		
		




