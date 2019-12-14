package mole;

import javafx.scene.control.Button;

public class MoleButton extends Button implements Runnable {
	
	Whacamole mole = new Whacamole();
	
	public MoleButton(Whacamole mole) {
		this.mole = mole;
		
		
	}

}
