package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class PriceTests {

	@ParameterizedTest
	@ValueSource(ints = {1, 99 })
	public void getFrequentRenterPointsShouldAlwaysTheSameReturnConstant(int daysRented) {
		// given
		Price concretePrice = new ConcretePriceImplForTesting();

		// when
		int resultFrequentRenterPoints = concretePrice.getFrequentRenterPoints(daysRented);

		// then
		assertEquals(1, resultFrequentRenterPoints,
				"getFrequentRenterPoints should return always '1' no matter which int is passed.");
	}
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0})
	public void getFrequentRenterPointsShouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given
				Price concretePrice = new ConcretePriceImplForTesting();

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			concretePrice.getFrequentRenterPoints(daysRented);
		});
	}
	

}
