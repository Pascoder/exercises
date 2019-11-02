package country;

import java.util.ArrayList;
import java.util.Arrays;

public class Country {
	// Test data
	public static final ArrayList<Country> countries = new ArrayList<>(Arrays.asList(new Country("Switzerland", 41285, 8526932),
			new Country("Germany", 357021, 82887000), new Country("France", 675417, 67372000), 
			new Country("Austria", 83858, 8857960 )));
	
	// The Country class
	private final String name;
	private double area;
	private  int popuation = 0;

	public Country(String name, double area) {
		this.name = name;
		this.area = area;
	}
	
	public Country(String name, double area, int population) {
		this.name = name;
		this.area = area;
		this.popuation = population;
	}

	public String getName() {
		return name;
	}

	public double getArea() {
		return area;
	}
	
	public int getPopulation() {
		return this.popuation;
	}

	@Override
	public String toString() {
		return String.format("%s (%.2f km2, %)", name, area);
	}
}