package movieCollection;

import javafx.application.Application;
import javafx.stage.Stage;

public class MovieCollection extends Application{
	private MovieCollectionView view;
	private MovieCollectionController controller;
	private MovieCollectionModel model;
	
	

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.model = new MovieCollectionModel();
		this.view = new MovieCollectionView(stage,model);
		this.controller = new MovieCollectionController(view, model);
		view.start();
		
		
	}

}
