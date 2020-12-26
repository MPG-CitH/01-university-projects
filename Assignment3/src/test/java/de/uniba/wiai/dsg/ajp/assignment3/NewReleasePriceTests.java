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

public class NewReleasePriceTests {

	private Price newReleasePrice;

	@BeforeEach
	public void setup() throws Exception {

		newReleasePrice = new NewReleasePrice();

	}

	@AfterEach
	public void tearDown() throws Exception {
		newReleasePrice = null;
	}

	@ParameterizedTest
	@MethodSource("differentValuesForDaysRentedForTestingGetCharge")
	public void getChargeShouldReturnCorrectValue(int daysRented, double expectedResult, String s) {
		// given setup() method

		// when
		double returnedResult = newReleasePrice.getCharge(daysRented);

		// then
		assertEquals(returnedResult, expectedResult, s);

	}
	
	@ParameterizedTest
	@ValueSource(ints = {-2, 0})
	public void getChargeSchouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given setup() method

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			newReleasePrice.getCharge(daysRented);
		});
	}

	@ParameterizedTest
	@MethodSource("differentValuesForDaysRentedForTestingGetFrequentRenterPoints")
	public void checkCorrectOverridingAndReturnValueOfGetFrequentRenterPoints(int daysRented, int expectedResult,
			String s) {
		// given setup() method

		// when
		double returnedResult = newReleasePrice.getFrequentRenterPoints(daysRented);

		// then
		assertEquals(returnedResult, expectedResult, s);

	}
	
	@ParameterizedTest
	@ValueSource(ints = {-2, 0})
	public void getFrequentRenterPointsSchouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given setup() method

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			newReleasePrice.getCharge(daysRented);
		});
	}

	public static List<Arguments> differentValuesForDaysRentedForTestingGetFrequentRenterPoints() {
		return List.of(Arguments.of(1, 1, "Returned result should have been '1'. The passed param was '1'."),
				Arguments.of(2, 2, "Returned result should have been '2'. The passed param was '2'."),
				Arguments.of(3, 2, "Returned result should have been '2'. The passed param was '3'."),
				Arguments.of(10, 2, "Returned result should have been '2'. The passed param was '10'."));
	}

	public static List<Arguments> differentValuesForDaysRentedForTestingGetCharge() {
		return List.of(Arguments.of(1, 3, "Returned result should have been '3'. The passed param was '1'."),
				Arguments.of(2, 6, "Returned result should have been '6'. The passed param was '2'."),
				Arguments.of(3, 9, "Returned result should have been '9'. The passed param was '3'."),
				Arguments.of(10, 30, "Returned result should have been '30'. The passed param was '10'."));
	}

}
