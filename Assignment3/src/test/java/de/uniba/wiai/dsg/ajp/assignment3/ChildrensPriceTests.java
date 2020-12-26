package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChildrensPriceTests {
	
	@ParameterizedTest
	@MethodSource("differentValuesForDaysRented")
	public void getChargeShouldReturnCorrectValue(int daysRented, double expectedResult) {
		// given
		Price childrensPrice = new ChildrensPrice();

		// when
		double returnedResult = childrensPrice.getCharge(daysRented);

		// then
		assertEquals(returnedResult, expectedResult);

	}
	
	public static List<Arguments> differentValuesForDaysRented() {
		return List.of(Arguments.of(1, 1.5, "Returned result should have been '1.5'. The passed param was '1'."),
				Arguments.of(2, 1.5, "Returned result should have been '1.5'. The passed param was '2'."),
				Arguments.of(3, 3.5, "Returned result should have been '3.5'. The passed param was '3'."),
				Arguments.of(10, 17.5, "Returned result should have been '17.5'. The passed param was '10'."));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-2, 0})
	public void getChargeSchouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given
		Price childrensPrice = new ChildrensPrice();

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			childrensPrice.getCharge(daysRented);
		});
	}
	

}
