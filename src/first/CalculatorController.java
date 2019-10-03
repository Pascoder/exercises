package first;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.Event;

public class CalculatorController {
	final private CalculatorModel model;
	final private CalculatorView view;
	protected String totaltext = "";
	protected String ergebnis [] = new String [10];
	protected int counter = 0;

	
public CalculatorController(CalculatorModel model, CalculatorView view) {
	this.model = model;
	this.view = view;
	
	view.eins.setOnAction(this::einsProcess);
	view.zwei.setOnAction(this::zweiProcess);
	view.drei.setOnAction(this::dreiProcess);
	view.vier.setOnAction(this::vierProcess);
	view.fuenf.setOnAction(this::fuenfProcess);
	view.sechs.setOnAction(this::sechsProcess);
	view.sieben.setOnAction(this::siebenProcess);
	view.acht.setOnAction(this::achtProcess);
	view.neun.setOnAction(this::neunProcess);
	view.gleich.setOnAction(this::gleichProcess);
	view.nulll.setOnAction(this::nulllProcess);
	view.c.setOnAction(this::cProcess);
	view.plus.setOnAction(this::plusProcess);
}
public void einsProcess(Event eins) {
//wenn zahl gedrückt wird ausgeben
this.counter++;//um zu wissen wie viel zahlen schon gedrückt wurden vor C (deleten)
setTotalText("1");


	
}
public void zweiProcess(Event zwei) {
	this.counter++;	
	setTotalText("2");

	int zwei1 = 2;
	
}
public void dreiProcess(Event drei) {
	this.counter++;
	setTotalText("3");
	int drei1 = 3;
	
}
public void vierProcess(Event vier) {
	this.counter++;
	setTotalText("4");
	int vier1 = 4;
	
}
public void fuenfProcess(Event fuenf) {
	this.counter++;
	setTotalText("5");
	int fuenf1 = 5;
	
}
public void sechsProcess(Event sechs) {
	this.counter++;
	setTotalText("6");
	int sechs1 = 6;
}
public void siebenProcess(Event sieben) {
	this.counter++;
	setTotalText("7");
	int sieben1 = 7;
}
public void achtProcess(Event acht) {
	this.counter++;
	setTotalText("8");
	int acht1 = 8;
}
public void neunProcess(Event neun) {
	this.counter++;
	setTotalText("9");
	int neun1 = 9;
}
public void gleichProcess(Event gleich) {
	//wenn gleich gedrückt wird muss berechnet werden
	this.counter = 0;
	String ziel = model.Umformatieren(this.totaltext);
	view.ausgabe.setText(ziel); //Endgülitges resultat
	this.totaltext = ziel;
	
}
public void nulllProcess(Event nulll) {
	this.counter++;
	setTotalText("0");
}
public void cProcess(Event c) {
	this.counter = 0 ;
	setTotalText("");
	view.ausgabe.clear();
	this.ergebnis  = null;
	
	
}
public void plusProcess(Event plus) {
	this.counter++;
	setTotalText("+");
}
public void setTotalText(String zahlen) {
	//Hier wird das ausgabe TextFeld generiert
	if(counter !=0) {
		this.totaltext += zahlen;
	  view.ausgabe.setText(totaltext);
	}else {
		this.totaltext = "";
	}
	
  
}



}