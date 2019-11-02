package country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CountryMain {

	public static void main(String[] args) {
		ArrayList<Country> countries = new ArrayList<>();
		countries.add(new Country("Switzerland", 41285, 8526932));
		countries.add(new Country("Germany", 357021, 82887000));
		countries.add(new Country("France", 675417, 67372000));
		countries.add(new Country("Austria", 83858, 8857960));
		
		Collections.sort(countries,(c1,c2)-> Integer.compare(c1.getPopulation(),c2.getPopulation()));
		
		countries.forEach(c->System.out.println(c));

	}

}
