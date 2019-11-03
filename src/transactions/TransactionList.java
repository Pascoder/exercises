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
        		.map(Transaction::getTrader)
        		.filter(t -> t.getCity().equals(city))
        		.distinct()
        		.sorted(Comparator.comparing(Trader::getName))
        		.collect(Collectors.toList());
    }

	/**
	 * @param city the city
	 * @return true if there are any trader based in given city
	 */
	public boolean traderInCity(String city) {
        return allTransactions.stream()
        		.anyMatch(t -> t.getTrader().getCity().equals(city));
    }

	/**
	 * @param from the trader's current location
	 * @param to   the trader's new location
	 */
	public void relocateTraders(String from, String to) {
		allTransactions.stream()
        .map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals(from))
        .forEach(trader -> trader.setCity(to));

	}

	/**
	 * @return the highest value in all the transactions
	 */
	public int highestValue() {
		return allTransactions.stream()
		.map(Transaction::getValue)
		.reduce(0, Integer::max);
		
	}

	/**
	 * @return a string of all traders’ names sorted alphabetically
	 */
	public String traderNames() {
        return allTransactions.stream()
        		.map(t -> t.getTrader().getName())
        		.distinct()
        		.sorted()
        		.collect(Collectors.joining());
    }
}
