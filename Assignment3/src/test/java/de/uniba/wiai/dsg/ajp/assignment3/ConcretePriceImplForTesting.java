package de.uniba.wiai.dsg.ajp.assignment3;

//implementation of the abstract methods of the abstract class 'Price' for testing

public class ConcretePriceImplForTesting extends Price {
	@Override
	public double getCharge(int daysRented) {
		return 3.5;
	}

	@Override
	public PriceCode getPriceCode() {
		return PriceCode.REGULAR;
	}

}
