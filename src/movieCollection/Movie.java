package movieCollection;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	private final StringProperty name = 		new SimpleStringProperty();
	private final StringProperty genre = 		new SimpleStringProperty();
	private final StringProperty number = 		new SimpleStringProperty();
	private final StringProperty language = 	new SimpleStringProperty();
	private final StringProperty regisseur = 	new SimpleStringProperty();
	private final StringProperty owner = 		new SimpleStringProperty();
	private final StringProperty year = 		new SimpleStringProperty();
	private final StringProperty time = 		new SimpleStringProperty();
	private final StringProperty location = 	new SimpleStringProperty();
	private final StringProperty starring = 	new SimpleStringProperty();
	
	
	public Movie (String[] line) {
		setName(line[0]);
		setGenre(line[1]);
		setNumber(line[2]);
		setLanguage(line[3]);
		setRegisseur(line[4]);
		setOwner(line[5]);
		setYear(line[6]);
		setTime(line[7]);
		setLocation(line[8]);
		setStarring(line[9]);
		
	}


	public final StringProperty nameProperty() {
		return this.name;
	}
	


	public final String getName() {
		return this.nameProperty().get();
	}
	


	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	


	public final StringProperty genreProperty() {
		return this.genre;
	}
	


	public final String getGenre() {
		return this.genreProperty().get();
	}
	


	public final void setGenre(final String genre) {
		this.genreProperty().set(genre);
	}
	


	public final StringProperty numberProperty() {
		return this.number;
	}
	


	public final String getNumber() {
		return this.numberProperty().get();
	}
	


	public final void setNumber(final String number) {
		
		this.numberProperty().set(number);
	}
	


	public final StringProperty languageProperty() {
		return this.language;
	}
	


	public final String getLanguage() {
		return this.languageProperty().get();
	}
	


	public final void setLanguage(final String language) {
		this.languageProperty().set(language);
	}
	


	public final StringProperty regisseurProperty() {
		return this.regisseur;
	}
	


	public final String getRegisseur() {
		return this.regisseurProperty().get();
	}
	


	public final void setRegisseur(final String regisseur) {
		this.regisseurProperty().set(regisseur);
	}
	


	public final StringProperty ownerProperty() {
		return this.owner;
	}
	


	public final String getOwner() {
		return this.ownerProperty().get();
	}
	


	public final void setOwner(final String owner) {
		this.ownerProperty().set(owner);
	}
	


	public final StringProperty yearProperty() {
		return this.year;
	}
	


	public final String getYear() {
		return this.yearProperty().get();
	}
	


	public final void setYear(final String year) {
		this.yearProperty().set(year);
	}
	


	public final StringProperty timeProperty() {
		return this.time;
	}
	


	public final String getTime() {
		return this.timeProperty().get();
	}
	


	public final void setTime(final String time) {
		this.timeProperty().set(time);
	}
	


	public final StringProperty starringProperty() {
		return this.starring;
	}
	


	public final String getStarring() {
		return this.starringProperty().get();
	}
	


	public final void setStarring(final String starring) {
		this.starringProperty().set(starring);
	}


	public final StringProperty locationProperty() {
		return this.location;
	}
	


	public final String getLocation() {
		return this.locationProperty().get();
	}
	


	public final void setLocation(final String location) {
		this.locationProperty().set(location);
	}


	@Override
	public String toString() {
		return infoAsLine();
	}


	
		public String infoAsLine() {
			return String.join("!",
			getName(),
			getGenre(),
			getNumber(),
			getLanguage(),
			getRegisseur(),
			getOwner(),
			getYear(),
			getTime(),
			getLocation(),
			getStarring()
			);	
			
		
		}

}
