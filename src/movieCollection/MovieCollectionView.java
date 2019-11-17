package movieCollection;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MovieCollectionView {
	private final MovieCollectionModel model;
	private final Stage stage;
	
	private TableView<Movie> tblResult;
		
	

	public MovieCollectionView(Stage stage, MovieCollectionModel model) {
		this.model = model;
		this.stage = stage;
		
		HBox box = new HBox();
		Button btn = new Button("Random Movie");
		Label label = new Label();
		
		box.getChildren().addAll(btn,label);
		btn.setOnAction((event) -> label.setText(model.randomMovie()));
		
		tblResult = createTableView();
		
		

		GridPane root = new GridPane();
		root.add(box, 0, 0);
		root.add(tblResult, 0, 1);
	
		tblResult.prefHeightProperty().bind(stage.heightProperty());
		tblResult.prefWidthProperty().bind(stage.widthProperty());
		
		Scene scene = new Scene(root);
		stage.setHeight(400);
		stage.setWidth(600);
		stage.setScene(scene);
		stage.setTitle("Movie Collection Manager 2000");
		
		
	}
	
	public void start() {
		stage.show();

	}
	

	private TableView<Movie> createTableView() {
		TableView<Movie> tableView = new TableView<>(model.getResults());
		
		TableColumn<Movie, String> colName = new TableColumn<>("Name");
		tableView.getColumns().add(colName);
		
		TableColumn<Movie, String> colGenre = new TableColumn<>("Genre");
		tableView.getColumns().add(colGenre);
		
		TableColumn<Movie, String> colNumber = new TableColumn<>("Number");
		tableView.getColumns().add(colNumber);
		
		TableColumn<Movie, String> colLanguage = new TableColumn<>("Language");
		tableView.getColumns().add(colLanguage);
		
		TableColumn<Movie, String> colRegisseur = new TableColumn<>("Regisseur");
		tableView.getColumns().add(colRegisseur);
		
		TableColumn<Movie, String> colOwner = new TableColumn<>("Owner");
		tableView.getColumns().add(colOwner);
		
		TableColumn<Movie, String> colYear = new TableColumn<>("Year");
		tableView.getColumns().add(colYear);
		
		TableColumn<Movie, String> colTime = new TableColumn<>("Time");
		tableView.getColumns().add(colTime);
		
		TableColumn<Movie, String> colLocation = new TableColumn<>("Location");
		tableView.getColumns().add(colLocation);
		
		TableColumn<Movie, String> colStarring = new TableColumn<>("Starring");
		tableView.getColumns().add(colStarring);
		
		
		colName.setCellValueFactory(cell -> cell.getValue().nameProperty());
		colGenre.setCellValueFactory(cell -> cell.getValue().genreProperty());
		colNumber.setCellValueFactory(cell -> cell.getValue().numberProperty());
		colLanguage.setCellValueFactory(cell -> cell.getValue().languageProperty());
		colRegisseur.setCellValueFactory(cell -> cell.getValue().regisseurProperty());
		colOwner.setCellValueFactory(cell -> cell.getValue().ownerProperty());
		colYear.setCellValueFactory(cell -> cell.getValue().yearProperty());
		colTime.setCellValueFactory(cell -> cell.getValue().timeProperty());
		colLocation.setCellValueFactory(cell -> cell.getValue().locationProperty());
		colStarring.setCellValueFactory(cell -> cell.getValue().starringProperty());
		
//		tableView.getColumns().addAll(colName,colGenre, colNumber, 
//										colLanguage, colRegisseur,colOwner,colYear,
//										colTime,colLocation,colStarring);
		
		
		return tableView;
		
	}
	
	
}
