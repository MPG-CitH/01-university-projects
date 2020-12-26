package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerTestsStatement {

	@BeforeAll
	public static void setup() throws Exception {
		FileCreator.setupStatement();
	}

	@Test
	public void statementFromNormalCustomerWithEmptyRentalList() {

		Customer customer = new Customer("User2");

		String result = customer.statement();

		String expectedResult = FileCreator.getStatementWithoutRentalList();

		assertEquals(expectedResult, result,
				"String statement should be correct according to customer data of a normal customer with no movies in rental list.");

	}

	@Test
	public void StatementFromNormalCustomerWithFilledRentalList() {

		// given
		Customer customer = new Customer("User1");

		List<Rental> rentalList1 = new LinkedList<Rental>();
		Rental stubRental1a = mock(Rental.class);
		Rental stubRental1b = mock(Rental.class);
		Rental stubRental1c = mock(Rental.class);
		Rental stubRental1d = mock(Rental.class);

		Movie stubMovie1a = mock(Movie.class);
		Movie stubMovie1b = mock(Movie.class);
		Movie stubMovie1c = mock(Movie.class);
		Movie stubMovie1d = mock(Movie.class);

		when(stubRental1a.getFrequentRenterPoints()).thenReturn(50);
		when(stubRental1b.getFrequentRenterPoints()).thenReturn(5);
		when(stubRental1c.getFrequentRenterPoints()).thenReturn(8);
		when(stubRental1d.getFrequentRenterPoints()).thenReturn(2);

		when(stubRental1a.getMovie()).thenReturn(stubMovie1a);
		when(stubMovie1a.getTitle()).thenReturn("Fast and Furious 1");
		when(stubMovie1a.getPictureQuality()).thenReturn(PictureQuality.RES_4K);
		when(stubRental1b.getMovie()).thenReturn(stubMovie1b);
		when(stubMovie1b.getTitle()).thenReturn("Fast and Furious 2");
		when(stubMovie1b.getPictureQuality()).thenReturn(PictureQuality.RES_4K);
		when(stubRental1c.getMovie()).thenReturn(stubMovie1c);
		when(stubMovie1c.getTitle()).thenReturn("Fast and Furious 3");
		when(stubMovie1c.getPictureQuality()).thenReturn(PictureQuality.RES_HD);
		when(stubRental1d.getMovie()).thenReturn(stubMovie1d);
		when(stubMovie1d.getTitle()).thenReturn("Fast and Furious 4");
		when(stubMovie1d.getPictureQuality()).thenReturn(PictureQuality.RES_HD);

		when(stubRental1a.getCharge()).thenReturn(1.5);
		when(stubRental1b.getCharge()).thenReturn(3.0);
		when(stubRental1c.getCharge()).thenReturn(5.5);
		when(stubRental1d.getCharge()).thenReturn(16.0);

		rentalList1.add(stubRental1a);
		rentalList1.add(stubRental1b);
		rentalList1.add(stubRental1c);
		rentalList1.add(stubRental1d);

		customer.setRentals(rentalList1);

		// when
		String result = customer.statement();

		// then

		String expectedResult = FileCreator.getStatementWithRentalList();

		assertEquals(expectedResult, result,
				"String statement should be correct according to customer data of a normal customer with movies in rental list.");

	}

	@Test
	public void statementFromPremiumCustomerWithFilledRentalList() {

		// given
		Customer customer = new Customer("User1");

		List<Rental> rentalList1 = new LinkedList<Rental>();
		Rental stubRental1a = mock(Rental.class);
		Rental stubRental1b = mock(Rental.class);
		Rental stubRental1c = mock(Rental.class);
		Rental stubRental1d = mock(Rental.class);

		Movie stubMovie1a = mock(Movie.class);
		Movie stubMovie1b = mock(Movie.class);
		Movie stubMovie1c = mock(Movie.class);
		Movie stubMovie1d = mock(Movie.class);

		when(stubRental1a.getFrequentRenterPoints()).thenReturn(50);
		when(stubRental1b.getFrequentRenterPoints()).thenReturn(5);
		when(stubRental1c.getFrequentRenterPoints()).thenReturn(8);
		when(stubRental1d.getFrequentRenterPoints()).thenReturn(2);

		when(stubRental1a.getMovie()).thenReturn(stubMovie1a);
		when(stubMovie1a.getTitle()).thenReturn("Fast and Furious 1");
		when(stubMovie1a.getPictureQuality()).thenReturn(PictureQuality.RES_4K);
		when(stubRental1b.getMovie()).thenReturn(stubMovie1b);
		when(stubMovie1b.getTitle()).thenReturn("Fast and Furious 2");
		when(stubMovie1b.getPictureQuality()).thenReturn(PictureQuality.RES_4K);
		when(stubRental1c.getMovie()).thenReturn(stubMovie1c);
		when(stubMovie1c.getTitle()).thenReturn("Fast and Furious 3");
		when(stubMovie1c.getPictureQuality()).thenReturn(PictureQuality.RES_HD);
		when(stubRental1d.getMovie()).thenReturn(stubMovie1d);
		when(stubMovie1d.getTitle()).thenReturn("Fast and Furious 4");
		when(stubMovie1d.getPictureQuality()).thenReturn(PictureQuality.RES_HD);

		when(stubRental1a.getCharge()).thenReturn(1.5);
		when(stubRental1b.getCharge()).thenReturn(3.0);
		when(stubRental1c.getCharge()).thenReturn(5.5);
		when(stubRental1d.getCharge()).thenReturn(16.0);

		rentalList1.add(stubRental1a);
		rentalList1.add(stubRental1b);
		rentalList1.add(stubRental1c);
		rentalList1.add(stubRental1d);

		customer.setRentals(rentalList1);
		customer.setCustomerType(CustomerType.PREMIUM);

		// when
		String result = customer.statement();

		// then

		String expectedResult = FileCreator.getStatementWithRentalListPremium();

		assertEquals(expectedResult, result,
				"String statement should be correct according to customer data of a premium customer with movies in rental list.");
	}

}
