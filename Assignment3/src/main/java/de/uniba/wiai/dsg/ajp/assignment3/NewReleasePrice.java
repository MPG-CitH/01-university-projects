package de.uniba.wiai.dsg.ajp.assignment3;

public class NewReleasePrice extends Price {

	@Override
	double getCharge(int daysRented) {
		if (daysRented > 0) {
			return daysRented * 3;
		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}
	}

	@Override
	int getFrequentRenterPoints(int daysRented) {
		if (daysRented > 0) {
			if (daysRented > 1) {
				return 2;
			} else {
				return 1;
			}
		} else {
			throw new IllegalArgumentException("Error. 'daysRented' must be greater than 0.");
		}
	}

	@Override
	PriceCode getPriceCode() {
		return PriceCode.NEW_RELEASE;
	}

}
