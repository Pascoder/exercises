package first;

public class CalculatorModel {

	private int sum;
	private int num1;
	private int num2;
	
	
	protected CalculatorModel() {	
	}
		
	public int getSum(int num1, int num2) {
		sum = num1+num2;
		return sum;
	}		
	
	
	
	
	
}


