package colors;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Color_View {
	private Stage stage;
	private Color_Model model;
	

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
		ColorPicker colorPicker = new ColorPicker();
		Slider redSlider = new Slider();
		Label redLabel = new Label("red");
		Slider blueSlider = new Slider();
		Label blueLabel = new Label("blue");
		Slider greenSlider = new Slider();
		Label greenLabel = new Label("green");
		
		sliderArea.getChildren().addAll(redSlider, blueSlider, greenSlider);
		labelArea.getChildren().addAll(redLabel, blueLabel, greenLabel);
		topArea.getChildren().addAll(sliderArea, labelArea );
		
		root.getChildren().addAll(topArea,saveBtn,colorPicker,list);
		
		
		
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		
		
		
	}

	public void start() {
		stage.show();
	}

	
	public Stage getStage() {
		return stage;
	}
	
	
	
	
	
	
	
}
