package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.LinkedList;
import java.util.List;

public class TestCustomer {
	public static Customer create() {
		Customer testCustomer = new Customer("John Doe");
		testCustomer.setRentals(createTestRentals());
		return testCustomer;
	}

	private static Rental createTestRental(String title, PriceCode priceCode, int daysRented, PictureQuality pictureQuality) {
		Movie movie = new Movie(title, priceCode, pictureQuality);
		Rental rental = new Rental();
		rental.setMovie(movie);
		rental.setDaysRented(daysRented);

		return rental;
	}

	private static List<Rental> createTestRentals() {
		List<Rental> testRentals = new LinkedList<Rental>();

		testRentals.add(createTestRental("Jaws", PriceCode.REGULAR, 2, PictureQuality.RES_HD));
		testRentals.add(createTestRental("Inception", PriceCode.REGULAR, 5, PictureQuality.RES_HD));
		testRentals.add(createTestRental("Parasite", PriceCode.NEW_RELEASE, 1, PictureQuality.RES_4K));
		testRentals.add(createTestRental("1917", PriceCode.NEW_RELEASE, 5, PictureQuality.RES_4K));
		testRentals.add(createTestRental("Spirited Away", PriceCode.CHILDRENS, 2, PictureQuality.RES_HD));
		testRentals.add(createTestRental("The Wizard of Oz", PriceCode.CHILDRENS, 5, PictureQuality.RES_HD));

		return testRentals;
	}
}
