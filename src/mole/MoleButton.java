package mole;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoleButton extends Button implements Runnable, EventHandler<ActionEvent> {
	final static Image MOLE_IMG = new Image(MoleButton.class.getResourceAsStream("mole.gif"));
	final static Image EMPTY_IMG = new Image(MoleButton.class.getResourceAsStream("empty.gif"));
	
	private Whacamole main;
	private Thread t;
	private ImageView moleImage = new ImageView(MOLE_IMG);
	private ImageView emptyImage = new ImageView(EMPTY_IMG);
	
	
	public MoleButton(Whacamole main) {
		super();
		this.main = main;
		this.setDisable(false);
		this.setGraphic(emptyImage);
		this.setOnAction(this);
		
		t = new Thread(this);
		t.start();
		
		
	}

	@Override
	public void run() {
		while(true) {
			this.setDisable(Math.random() < 0.7);
            Platform.runLater(() -> {
                if (this.isDisabled()) {
                    this.setGraphic(emptyImage);
                } else {
                    this.setGraphic(moleImage);
                }
            });
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
			
		}
		
	}

	@Override
	public void handle(ActionEvent event) {
		main.whack();
		
		
	}

	

}
