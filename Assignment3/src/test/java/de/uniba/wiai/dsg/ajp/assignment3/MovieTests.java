package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MovieTests {
	private Movie movie;

	@BeforeEach
	public void setup() throws Exception {

		movie = new Movie("Batman", PriceCode.REGULAR, PictureQuality.RES_HD);

	}

	@AfterEach
	public void tearDown() throws Exception {
		movie = null;
	}

	@Test
	public void titleShouldHaveCorrectInitializationValue() {
		// given and when setup() method

		// then
		assertEquals("Batman", movie.getTitle(), "movie has to be initialized with title 'Batman'.");
	}

	@Test
	public void PriceCodeShouldHaveCorrectInitializationValue() {
		// given and when setup() method

		// then
		assertEquals(PriceCode.REGULAR, movie.getPriceCode(), "movie has to be initialized with PriceCode 'REGULAR'.");
	}

	@Test
	public void PictureQualityShouldHaveCorrectInitializationValue() {
		// given and when setup() method

		// then
		assertEquals(PictureQuality.RES_HD, movie.getPictureQuality(),
				"movie has to be initialized with PictureQuality 'RES_HD'.");
	}

	@Test
	public void titleShouldBeSetAccordingToSetValue() {
		// given setup() method

		// when
		movie.setTitle("Dirty Dancing");

		// then
		assertEquals("Dirty Dancing", movie.getTitle(), "movie title has to be set to 'Dirty Dancing'.");
	}

	// TODO getFrequentRenterPoints und get PriceCode sind nicht auf Interaktion mit
	// abstrakter Klasse Price getestet. Ideen?

	// TODO getCharge testen

	@ParameterizedTest
	@MethodSource("differentValuesForPriceCode")
	public void priceCodeShouldBeSetAccordingToSetValue(PriceCode p, String s) {
		// given setup method ()

		movie.setPriceCode(p);

		// when
		PriceCode result = movie.getPriceCode();

		// then
		assertEquals(p, result, s);

	}

	public static List<Arguments> differentValuesForPriceCode() {
		return List.of(Arguments.of(PriceCode.REGULAR, "PriceCode should be set to 'REGULAR'."),
				Arguments.of(PriceCode.CHILDRENS, "PriceCode should be set to 'CHILDRENS'."),
				Arguments.of(PriceCode.NEW_RELEASE, "PriceCode should be set to 'NEW_RELEASE'."));
	}

	@Test
	public void setPriceCodeShouldThrowExceptionWhenPassingNotExistingPriceCode() {
		// given setup method ()

		// when and then

		assertThrows(IllegalArgumentException.class, () -> {
			movie.setPriceCode(PriceCode.valueOf("CHEAP"));
		});

	}
	
	@ParameterizedTest
	@MethodSource("differentQualitiesForCharge")
	public void getChargeShouldReturnCorrectValue(PictureQuality quality, double expectedResult) {
		// given
		Movie qualityMovie = new Movie("The Lighthouse", PriceCode.REGULAR, quality);

		// when
		double returnedResult = qualityMovie.getCharge(1);

		// then
		assertEquals(returnedResult, expectedResult);

	}
	
	public static List<Arguments> differentQualitiesForCharge() {
		return List.of(Arguments.of(PictureQuality.RES_HD, 2, "Returned result should have been '2'. The passed param was 'RES_HD'."),
				Arguments.of(PictureQuality.RES_4K, 4, "Returned result should have been '4'. The passed param was 'RES_4K'."));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0})
	public void getChargeShouldThrowExceptionWhenPassingNonPositiveValues(int daysRented) {
		// given setup() method
		
		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			movie.getCharge(daysRented);
		});
	}
	
	@Test
	public void setPictureQualityShouldThrowExceptionWhenPassingNull() {
		// given setup() method

		// when and then
		assertThrows(IllegalArgumentException.class, () -> {
			movie.setPictureQuality(null);;
		});
	}

	@Test
	public void pictureQualityShouldBeSetAccordingToSetValue() {
		// given setup() method

		// when
		movie.setPictureQuality(PictureQuality.RES_4K);

		assertEquals(PictureQuality.RES_4K, movie.getPictureQuality(), "PictureQuality should be set to 'RES_4K'.");
	}

}
