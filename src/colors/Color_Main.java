package colors;

import javafx.application.Application;
import javafx.stage.Stage;

public class Color_Main extends Application {
	private Color_Model model;
	private Color_View view;
	private Color_Controller controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		model = new Color_Model();
		view = new Color_View(primaryStage, model);
		controller = new Color_Controller(model, view);
		
		view.start();
		
	}

}
