package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RegularPriceTests {

	private Price regularPrice;

	@BeforeEach
	public void setup() throws Exception {

		regularPrice = new RegularPrice();

	}

	@AfterEach
	public void tearDown() throws Exception {
		regularPrice = null;
	}

	@ParameterizedTest
	@MethodSource("differentValuesForDaysRented")
	public void getChargeShouldReturnCorrectValue(int daysRented, double expectedResult, String s) {
		// given setup() method

		// when
		double returnedResult = regularPrice.getCharge(daysRented);

		// then
		assertEquals(returnedResult, expectedResult, s);

	}

	@ParameterizedTest
	@ValueSource(ints = { -5, 0})
	public void getFrequentRenterPointsShouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given setup() method

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			regularPrice.getCharge(daysRented);
		});
	}

	public static List<Arguments> differentValuesForDaysRented() {
		return List.of(Arguments.of(1, 2, "Returned result should have been '2'. The passed param was '1'."),
				Arguments.of(2, 2, "Returned result should have been '2'. The passed param was '2'."),
				Arguments.of(3, 3.5, "Returned result should have been '3.5'. The passed param was '3'."),
				Arguments.of(5, 6.5, "Returned result should have been '6.5'. The passed param was '5'."),
				Arguments.of(10, 14, "Returned result should have been '14'. The passed param was '10'."));
	}

}
