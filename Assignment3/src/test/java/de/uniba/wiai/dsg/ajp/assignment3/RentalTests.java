package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RentalTests {

	private Rental rental;

	@BeforeEach
	public void setup() throws Exception {
		rental = new Rental();
	}

	@AfterEach
	public void tearDown() throws Exception {
		rental = null;
	}

	@Test
	public void movieShouldBeNullWhenNotInitialized() {
		// given and when: setup() method

		// then
		assertTrue(null == rental.getMovie(), "not initialized variable movie should be null");
	}

	@Test
	public void movieShouldHaveCorrectValueAfterSetting() {
		// given setup() method

		// when
		Movie mockedMovie = mock(Movie.class);
		rental.setMovie(mockedMovie);

		// then
		assertEquals(rental.getMovie(), mockedMovie, "movie has been set to mockedMovie and should have this value.");

	}
	
	@Test
	public void setMoviesShouldThrowExceptionWhenPassingNull() {
		// given setup() method

		// when
		Movie mockedMovie = null;

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			rental.setMovie(mockedMovie);
		});
	}


	@ParameterizedTest
	@ValueSource(ints = {1, 2, 7 })
	public void daysRentedShouldHaveCorrectValueAfterSetting(int daysRented) {
		// given setup() method and:
		rental.setDaysRented(daysRented);
		// when
		int result = rental.getDaysRented();

		// then
		String ResultToString = String.valueOf(result);
		assertEquals(daysRented, result, "daysRented has been set to" + ResultToString + "and should have this value.");

	}
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0})
	public void daysRentedShouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given setup() method
		
		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			rental.setDaysRented(daysRented);
		});
	}

	@Test
	public void getChargeShouldCallMovieGetChargeCorrectly() {
		// given setup() method and:
		Movie mockedMovie = mock(Movie.class);
		rental.setMovie(mockedMovie);

		// when
		rental.getCharge();

		// then
		verify(mockedMovie, times(1)).getCharge(anyInt());

		verifyNoMoreInteractions(mockedMovie);
	}

	@Test
	public void getFrequentedRenterPointsShouldCallMovieGetFrequentedRenterPointsCorrectly() {
		// given setup() method and:
		Movie mockedMovie = mock(Movie.class);
		rental.setMovie(mockedMovie);
		
		// when
		rental.getFrequentRenterPoints();

		// then
		verify(mockedMovie, times(1)).getFrequentRenterPoints(anyInt());

		verifyNoMoreInteractions(mockedMovie);

	}

}
