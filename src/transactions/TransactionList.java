package transactions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dieter Holz
 */
public class TransactionList {
	private final List<Transaction> allTransactions = new ArrayList<>();

	public void addTransaction(Transaction transaction) {
		allTransactions.add(transaction);
	}

	public int size() {
		return allTransactions.size();
	}

	public List<Transaction> transactionsInYear(int year) {
		return allTransactions.stream()
				.filter(t ->t.getYear()==year)
				.sorted(Comparator.comparing(Transaction::getValue))
				.collect(Collectors.toList());
			
    }

	public List<String> cities() {
        return allTransactions.stream()
        		.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(Collectors.toList());
    }

	/**
	 * @param city the trader's city
	 * @return all traders from given city sorted by name.
	 */
	public List<Trader> traders(String city) {
        return allTransactions.stream()
        		.map(t -> t.getTrader())
        		.filter(t -> t.getCity().equals(city))
        		.collect(Collectors.toList());
    }

	/**
	 * @param city the city
	 * @return true if there are any trader based in given city
	 */
	public boolean traderInCity(String city) {
        return allTransactions.stream()
        		.map(t -> t.getTrader())
        		.filter(t -> t.getCity().equals(city))
        		.collect(Collectors.toList());
    }

	/**
	 * @param from the trader's current location
	 * @param to   the trader's new location
	 */
	public void relocateTraders(String from, String to) {

	}

	/**
	 * @return the highest value in all the transactions
	 */
	public int highestValue() {
		return 0;
	}

	/**
	 * @return a string of all traders’ names sorted alphabetically
	 */
	public String traderNames() {
        return null;
    }
}
