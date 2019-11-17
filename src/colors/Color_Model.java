package colors;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class Color_Model {
	
	private IntegerProperty red = new SimpleIntegerProperty(255);
	private IntegerProperty green = new SimpleIntegerProperty(0);
	private IntegerProperty blue = new SimpleIntegerProperty(255);
	
	private final ObservableList<Color> colorList = FXCollections.observableArrayList();
	
	private final ObjectProperty<Color> color = new SimpleObjectProperty<>();

	
	public Color_Model() {
		
		
		
	}
	
	public void saveColor() {
		colorList.add(getColor());
	}
	
	public int getRed() {
		return red.get();
	}

	public IntegerProperty redProperty() {
		return red;
	}

	public void setRed(int red) {
		this.red.set(red);
	}

	public int getGreen() {
		return green.get();
	}

	public IntegerProperty greenProperty() {
		return green;
	}

	public void setGreen(int green) {
		this.green.set(green);
	}

	public int getBlue() {
		return blue.get();
	}

	public IntegerProperty blueProperty() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue.set(blue);
	}

	public Color getColor() {
		return color.get();
	}

	public ObjectProperty<Color> colorProperty() {
		return color;
	}

	public void setColor(Color color) {
		this.color.set(color);
	}

	public ObservableList<Color> getColorList() {
		return colorList;
	}
}
