package colors;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MyColor {
	
	private IntegerProperty red = new SimpleIntegerProperty(255);
	private IntegerProperty green = new SimpleIntegerProperty(0);
	private IntegerProperty blue = new SimpleIntegerProperty(255);
	
	
	public MyColor (IntegerProperty red,IntegerProperty green, IntegerProperty blue ) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}


	public final IntegerProperty redProperty() {
		return this.red;
	}
	


	public final int getRed() {
		return this.redProperty().get();
	}
	


	public final void setRed(final int red) {
		this.redProperty().set(red);
	}
	


	public final IntegerProperty greenProperty() {
		return this.green;
	}
	


	public final int getGreen() {
		return this.greenProperty().get();
	}
	


	public final void setGreen(final int green) {
		this.greenProperty().set(green);
	}
	


	public final IntegerProperty blueProperty() {
		return this.blue;
	}
	


	public final int getBlue() {
		return this.blueProperty().get();
	}
	


	public final void setBlue(final int blue) {
		this.blueProperty().set(blue);
	}
	

}
