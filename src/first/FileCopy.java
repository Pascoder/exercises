package first;

import javafx.application.Application;
import javafx.stage.Stage;

public class FileCopy extends Application {
	private FileCopyView view;
	private FileCopyController controller;
	private FileCopyModel model;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void init() {
		// Perform initialization here, or omit this method
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.model = new FileCopyModel();
		this.view = new FileCopyView(stage, model);
		this.controller = new FileCopyController(view, model);
		view.start();
	}
	
	@Override
	public void stop() {
		// Perform clean-up here, or omit this method
	}
}
