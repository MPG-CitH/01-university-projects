package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {

	abstract double getCharge(int daysRented);

	int getFrequentRenterPoints(int daysRented) {
		if (daysRented > 0) {
			return 1;
		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}
	}

	abstract PriceCode getPriceCode();

}
