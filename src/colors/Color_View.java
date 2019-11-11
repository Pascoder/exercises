package colors;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Color_View {
	private Stage stage;
	private Color_Model model;
	private Slider redSlider, blueSlider, greenSlider;
	

	public Color_View(Stage stage, Color_Model model) {
		this.stage = stage;
		this.model = model;
		
		
		
		stage.setTitle("So many Colors...");
		
		VBox root = new VBox();
		
		VBox sliderArea = new VBox();
		VBox labelArea = new VBox();
		HBox topArea = new HBox();
		ListView list = new ListView();
		Button saveBtn = new Button("Save Color");
		VBox savedArea = new VBox();
		Rectangle rectangle = new Rectangle();
		redSlider = new Slider(0,255,127.5);
		Label redLabel = new Label("red");
		blueSlider = new Slider(0,255,127.5);
		Label blueLabel = new Label("blue");
		greenSlider = new Slider(0,255,127.5);
		Label greenLabel = new Label("green");
		
		
		
		
		
		rectangle.setFill(Color.ALICEBLUE);
		rectangle.setHeight(200.0);
		rectangle.setWidth(200.0);
		
		sliderArea.getChildren().addAll(redSlider, blueSlider, greenSlider);
		labelArea.getChildren().addAll(redLabel, blueLabel, greenLabel);
		topArea.getChildren().addAll(sliderArea, labelArea );
		
		root.getChildren().addAll(topArea,saveBtn,rectangle,list);
		
		
		
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		
		
		
	}

	public void start() {
		stage.show();
	}

	
	public Stage getStage() {
		return stage;
	}

	public Slider getRedSlider() {
		return redSlider;
	}

	public void setRedSlider(Slider redSlider) {
		this.redSlider = redSlider;
	}

	public Slider getBlueSlider() {
		return blueSlider;
	}

	public void setBlueSlider(Slider blueSlider) {
		this.blueSlider = blueSlider;
	}

	public Slider getGreenSlider() {
		return greenSlider;
	}

	public void setGreenSlider(Slider greenSlider) {
		this.greenSlider = greenSlider;
	}
	
	
	
	
	
	
	
}
