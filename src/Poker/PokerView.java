package Poker;

import com.sun.prism.paint.Color;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PokerView {
//Verbindungen zu anderen Klassen erstellen
private Stage stage;
private PokerModel model;
//GridPane als 1. Pane festlegen
private GridPane pane;
//Elemenete für Pokerspiel erstellen (Button....
private Label player0,player1,twopair,highcard,points;
private Image card1,card2,card3,card4,card5,card6,card7,card8,card9,card10;
private Button shuffle,deal;
private ImageView imageview1, imageview2, imageview3, imageview4, imageview5, imageview6, imageview7, imageview8, imageview9, imageview10;
	
public PokerView(Stage stage, PokerModel model) {
	this.stage = stage;
	this.model = model;
	stage.setTitle("Poker MiniProject");
	this.pane = new GridPane();
	
	//Labels hinzufügen pane.add(...,spalte,zeile)
	this.player0 =  new Label("	Player 0");
	pane.add(player0, 2, 0);
	
	this.player1 = new Label("	Player 1");
	pane.add(player1, 7, 0);
	
	//PokerKarten hinzufügen (Bilder) !!@todo hier muss eine Variabel mit den verschieden Image Links reinkommen die per Random per zufall eine karte auswählt
	this.card1 = new Image("10_of_clubs.png");
	this.imageview1 = new ImageView(card1);
	this.pane.add(imageview1, 0, 1);
	this.imageview1.setFitHeight(100);
	this.imageview1.setFitWidth(100);
	
	this.card2 = new Image("10_of_diamonds.png");
	this.imageview2 = new ImageView(card2);
	this.pane.add(imageview2, 1, 1);
	this.imageview2.setFitHeight(100);
	this.imageview2.setFitWidth(100);
	
	this.card3 = new Image("10_of_spades.png");
	this.imageview3 = new ImageView(card3);
	this.pane.add(imageview3, 2, 1);
	this.imageview3.setFitWidth(100);
	this.imageview3.setFitHeight(100);
	
	this.card4 = new Image("2_of_clubs.png");
	this.imageview4 = new ImageView(card4);
	this.pane.add(imageview4, 3, 1);
	this.imageview4.setFitWidth(100);
	this.imageview4.setFitHeight(100);
	
	this.card5 = new Image("2_of_diamonds.png");
	this.imageview5 = new ImageView(card5);
	this.pane.add(imageview5, 4, 1);
	this.imageview5.setFitHeight(100);
	this.imageview5.setFitWidth(100);
	
	this.card6 = new Image("2_of_hearts.png");
	this.imageview6 = new ImageView(card6);
	this.pane.add(imageview6, 5, 1);
	this.imageview6.setFitHeight(100);
	this.imageview6.setFitWidth(100);
	
	this.card7 = new Image("2_of_spades.png");
	this.imageview7 = new ImageView(card7);
	this.pane.add(imageview7, 6, 1);
	this.imageview7.setFitHeight(100);
	this.imageview7.setFitWidth(100);
	
	this.card8 = new Image("3_of_clubs.png");
	this.imageview8 = new ImageView(card8);
	this.pane.add(imageview8, 7, 1);
	this.imageview8.setFitHeight(100);
	this.imageview8.setFitWidth(100);
	
	this.card9 = new Image("3_of_diamonds.png");
	this.imageview9 = new ImageView(card9);
	this.pane.add(imageview9, 8, 1);
	this.imageview9.setFitHeight(100);
	this.imageview9.setFitWidth(100);
	
	this.card10 = new Image("3_of_hearts.png");
	this.imageview10 = new ImageView(card10);
	this.pane.add(imageview10, 9, 1);
	this.imageview10.setFitHeight(100);
	this.imageview10.setFitWidth(100);
	
	//Label unterhalb von Pokerkarten @todo --> resultat der karten hier festlegen
	this.twopair = new Label("	Punkte");
	this.pane.add(twopair, 2, 2);
	
	this.highcard = new Label("	Punkte");
	this.pane.add(highcard, 7, 2);
	
	//Hbox für die 2 Buttons erstellen
	Label platzhalter1 = new Label("	");
	this.points = new Label("42");
	Label platzhalter = new Label("																																	");
	this.shuffle = new Button("shuffle");
	this.deal = new Button("deal");
	HBox hbox = new HBox();
	hbox.getChildren().addAll(platzhalter1, points,platzhalter, shuffle,deal);
	pane.add(hbox, 0, 3,11,11);
	
	
	//Scene setzen
	Scene scene = new Scene(pane,1050,200);//@todo remove grösse
	stage.setScene(scene);
	pane.setVgap(10);
	pane.setHgap(5);
	//@todo create CSS file for design features
	pane.setStyle("-fx-background-color: oldlace");
	hbox.setStyle("-fx-background-color: burlywood");
	
}
public void start() {
	this.stage.show();
}
}
