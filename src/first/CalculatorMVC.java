package first;

import Exercises.CalculatorModel;
import Exercises.CalculatorView;
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
	public void start(Stage PrimaryStage) {
		model = new CalculatorModel();
		view = new CalculatorView(PrimaryStage,model);
		//controller = new CalculatorController(model,view);
		view.start();
		
	}

}
