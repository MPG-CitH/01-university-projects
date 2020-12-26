package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CustomerTestsTotalChargeTotalRenterPoints {

	private Customer customer;

	@BeforeEach
	public void setup() {
		customer = new Customer("UserXY");
	}

	@AfterEach
	public void tearDown() throws Exception {
		customer = null;
	}

	@ParameterizedTest
	@MethodSource("differentValuesForCheckingInteraction")
	public void getTotalChargeShouldCallRentalGetChargeCorrectly(int numberOfRentals, int numberOfCalls, String s) {

		// given setup() method and:
		Rental mockedRental = mock(Rental.class);
		List<Rental> list = new LinkedList<Rental>();
		while (numberOfRentals > 0) {
			list.add(mockedRental);
			numberOfRentals--;
		}
		customer.setRentals(list);

		// when
		customer.getTotalCharge();

		// then
		verify(mockedRental, times(numberOfCalls)).getCharge();

		verifyNoMoreInteractions(mockedRental);
	}

	@ParameterizedTest
	@MethodSource("differentValuesForCheckingInteraction")
	public void getTotalFrequentRenterPointsShouldCallRentalGetTotalRenterPointsCorrectly(int numberOfRentals,
			int numberOfCalls, String s) {

		// given setup() method and:
		Rental mockedRental = mock(Rental.class);
		List<Rental> list = new LinkedList<Rental>();
		while (numberOfRentals > 0) {
			list.add(mockedRental);
			numberOfRentals--;
		}
		customer.setRentals(list);

		// when
		customer.getTotalFrequentRenterPoints();

		// then
		verify(mockedRental, times(numberOfCalls)).getFrequentRenterPoints();

		verifyNoMoreInteractions(mockedRental);
	}

	public static List<Arguments> differentValuesForCheckingInteraction() {
		return List.of(Arguments.of(1, 1,
				"There was 1 mockedRental in the list, so there should have been 1 interaction with the called method of the rental class."),
				Arguments.of(3, 3,
						"There were 3 mockedRentals in the list, so there should have been 3 interactions with the called method of the rental class."),
				Arguments.of(10, 10,
						"There were 10 mockedRentals in the List, so there should have been 10 interactions with the called method of the rental class.."));
	}

	@ParameterizedTest
	@MethodSource("differentValuesForCheckingCharge")
	public void getTotalChargeShouldGetTheSumOfTheChargesOfTheRentalsCorrectly(int numberOfRentals,
			double expectedResult, String s) {

		// given setup() method and:

		Rental stubRental1 = mock(Rental.class);
		Rental stubRental2 = mock(Rental.class);

		when(stubRental1.getCharge()).thenReturn(12.75);
		when(stubRental2.getCharge()).thenReturn(3.37);

		List<Rental> list = new LinkedList<Rental>();

		if (numberOfRentals == 2) {
			list.add(stubRental1);
			list.add(stubRental2);

			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalCharge(), s);
		}

		if (numberOfRentals == 1) {
			list.add(stubRental1);

			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalCharge(), s);
		}

		if (numberOfRentals == 0) {
			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalCharge(), s);
		}

	}

	public static List<Arguments> differentValuesForCheckingCharge() {
		return List.of(
				Arguments.of(1, 12.75,
						"There was 1 mockedRental in the list with charge 12.75, so total charge should be 12.75."),
				Arguments.of(2, 16.12,
						"There were 2 mockedRentals in the list with charge 12.75 and 3.37, so total charge should be 16.12."));
	}

	@ParameterizedTest
	@MethodSource("differentValuesForCheckingRenterPoints")
	public void getTotalFrequentRenterPointsShouldGetTheSumOfTheFrequentRenterPointsOfTheRentalsCorrectly(
			int numberOfRentals, int expectedResult, String s) {

		// given setup() method and:
		Rental stubRental1 = mock(Rental.class);
		Rental stubRental2 = mock(Rental.class);

		when(stubRental1.getFrequentRenterPoints()).thenReturn(7);
		when(stubRental2.getFrequentRenterPoints()).thenReturn(4);

		List<Rental> list = new LinkedList<Rental>();

		if (numberOfRentals == 2) {
			list.add(stubRental1);
			list.add(stubRental2);

			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalFrequentRenterPoints(), s);

		}

		if (numberOfRentals == 1) {
			list.add(stubRental1);

			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalFrequentRenterPoints(), s);
		}

		if (numberOfRentals == 0) {
			// when
			customer.setRentals(list);

			// then
			assertEquals(expectedResult, customer.getTotalFrequentRenterPoints(), s);
		}

	}

	public static List<Arguments> differentValuesForCheckingRenterPoints() {
		return List.of(Arguments.of(1, 7,
				"There was 1 mockedRental in the list with 7 frequent renter points, so total frequent renter points should be 7."),
				Arguments.of(2, 11,
						"There were 2 mockedRentals in the list with 7 and 4 frequent renter points, so total frequent renter points  should be 11."));
	}

}
