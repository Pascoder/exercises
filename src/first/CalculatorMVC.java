package first;

import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorMVC extends Application{
private CalculatorModel model;
private CalculatorView view;
private CalculatorController controller;

	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void start(Stage PrimaryStage) throws Exception{
		model = new CalculatorModel();
		view = new CalculatorView(PrimaryStage,model);
		controller = new CalculatorController(model,view);
		view.start();
	}

}