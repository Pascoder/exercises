package first;



public class CalculatorModel {
public CalculatorController controller;
	
public CalculatorModel() {
	
}

	public String  Umformatieren(String text) {
		String totalzahlen ="";
		int Lösung[] = new int [10];
		int endresultat = 0;
		
		for(int i = 0;i<text.length();i++) {
			
		char nummern = text.charAt(i);
		totalzahlen +=nummern;	
			
		}
		

		
		//splitten der zeichenkette
		
		String[] strValues = totalzahlen.split("\\+");//Zahlen werden im Array abgelegt
		for(int b =0;b<strValues.length;b++) {
			Lösung[b] = Integer.parseInt(strValues[b]);//String wird zu Int darum anderes Array nötig für berrechnung
		}
		
		for(int c = 0; c<Lösung.length;c++) {
			endresultat+= Lösung[c];//Berrechnung des Ergebnis
		}
		String endresultat1 = Integer.toString(endresultat);
		
		return endresultat1;//von hier weiter rechnen
		
		
		
	}
}


