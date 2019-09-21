package first;

import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorMVC extends Application{
	private CalculatorView view;
	private CalculatorController controller;
	private CalculatorModel model;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage)  {
		model = new CalculatorModel();
		view = newCalculatorView(primaryStage, model);
		controller = new CalculatorController(model, view);
		
		
	}

}
