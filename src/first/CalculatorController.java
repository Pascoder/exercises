package first;

public class CalculatorController {
	final private CalculatorModel model;
	final private CalculatorView view;
	private String Total;
	
	protected CalculatorController(CalculatorModel model, CalculatorView view) {
		this.model = model;
		this.view = view;
		this.Total="";
		
		
		//Eingabe der Zahlenwerte
	
	view.eins.setOnAction((event) -> {
		this.Total = Total + "1";
		view.ausgabe.setText(Total);	
	});
	
	view.zwei.setOnAction((event) -> {
		this.Total = Total + "2";
		view.ausgabe.setText(Total);	
	});
	
	view.drei.setOnAction((event) -> {
		this.Total = Total + "3";
		view.ausgabe.setText(Total);	
	});
	
	view.vier.setOnAction((event) -> {
		this.Total = Total + "4";
		view.ausgabe.setText(Total);	
	});
	
	view.fuenf.setOnAction((event) -> {
		this.Total = Total + "5";
		view.ausgabe.setText(Total);	
	});
	
	view.sechs.setOnAction((event) -> {
		this.Total = Total + "6";
		view.ausgabe.setText(Total);	
	});
	
	view.sieben.setOnAction((event) -> {
		this.Total = Total + "7";
		view.ausgabe.setText(Total);	
	});
	view.acht.setOnAction((event) -> {
		this.Total = Total + "8";
		view.ausgabe.setText(Total);	
	});
	
	view.neun.setOnAction((event) -> {
		this.Total = Total + "9";
		view.ausgabe.setText(Total);	
	});
	view.nulll.setOnAction((event) -> {
		this.Total = Total + "0";
		view.ausgabe.setText(Total);	
	});
	
	
	//Funktionstasten C,=,+
	
	view.c.setOnAction((event) -> {
		this.Total = "";
		view.ausgabe.setText(Total);
	});
	
	view.plus.setOnAction((event) -> {
		this.Total = Total + "+";
		view.ausgabe.setText("Total");
	});
	
//	view.gleich.setOnAction((event) -> {
//		this.Total = Total.split("\\+));"
//		view.ausgabe.setText(Total);
//	});
//	
	
	
	
	}
}
