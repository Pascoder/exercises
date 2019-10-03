package first;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorView {
//TextField muss protected sein
protected TextField ausgabe;
//Buttons muessen protected sein, nicht private
protected Button eins,zwei,drei,vier,fuenf,sechs,sieben,acht,neun,nulll,plus,gleich,c;
private Stage stage;
private CalculatorModel model;


public CalculatorView(Stage stage, CalculatorModel model) {
	this.stage = stage;
	this.model = model;
	stage.setTitle("Calculator");
	
	GridPane grid = new GridPane();
	
	//AusgabeFeld
	this.ausgabe =  new TextField();
	grid.add(ausgabe, 0, 0);
	grid.setColumnSpan(ausgabe, 4); //2 Zellen f�r Ausgabe Feld benutzen, damit Format passt
	
	//1. Zeile
	this.sieben = new Button ("  7  ");
	grid.add(sieben, 0, 1);
	this.acht = new Button ("  8  ");
	grid.add(acht, 1,1);
	this.neun = new Button ("  9  ");
	grid.add(neun, 2, 1);
	this.plus = new Button("  +  ");
	grid.add(plus, 3, 1);
	
	//2.Zeile
	this.vier = new Button("  4  ");
	grid.add(vier, 0, 2);
	this.fuenf = new Button ("  5  ");
	grid.add(fuenf, 1, 2);
	this.sechs = new Button ("  6  ");
	grid.add(sechs, 2, 2);
	this.c = new Button ("  C  ");
	grid.add(c, 3, 2);
	
	//3.Zeile
	this.drei = new Button ("  3  ");
	grid.add(drei, 0, 3);
	this.zwei = new Button("  2  ");
	grid.add(zwei, 1, 3);
	this.eins = new Button("  1  ");
	grid.add(eins, 2, 3);
	this.gleich = new Button("  =  \r\n" + 
			"");
	grid.add(gleich, 3, 3);
	grid.setRowSpan(gleich, 2);//Zeilen anpassen f�r =
	grid.setColumnSpan(gleich, 2);//Spalten anpassen f�r =
	
	//4.Zeile
	this.nulll = new Button("	      0 	    ");
	grid.add(nulll, 0, 4);
	grid.setColumnSpan(nulll, 3);
	
	
	//grid also die root wird der scene �bergeben
	Scene scene = new Scene(grid);
	
	//CSS Style Sheed
	scene.getStylesheets().add(
			getClass().getResource("Calculator.css").toExternalForm());
	stage.setScene(scene);
}


public void start() {
	stage.show();
	
}
}