package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTestsConstructorInitializationAndSettingOfVariables {

	private Customer customer;

	@BeforeEach
	public void setup() throws Exception {
		customer = new Customer("Peter Pan");
	}

	@AfterEach
	public void tearDown() throws Exception {
		customer = null;
	}

	@Test
	public void nameShouldHaveCorrectInitializationValue() {
		// given and when setup() method
		// then
		assertEquals("Peter Pan", customer.getName(), "customer has to be initialized with name 'Peter Pan'.");
	}

	@Test
	public void nameShouldBeSetAccordingToSetValue() {
		// given setup() method

		// when
		customer.setName("Tina Turner");

		// then
		assertEquals("Tina Turner", customer.getName(), "customer name has to be set to 'Tina Turner'.");
	}

	private List<Rental> createListWithRentals() {

		Rental mockedRental1 = mock(Rental.class);
		Rental mockedRental2 = mock(Rental.class);
		List<Rental> listWithMockedRentals = new LinkedList<Rental>();
		listWithMockedRentals.add(mockedRental1);
		listWithMockedRentals.add(mockedRental2);
		return listWithMockedRentals;
	}

	@Test
	public void listShouldBeSetAccordingToSetValue() {
		// given setup() method

		// when
		List<Rental> listWithMockedRentals = createListWithRentals();
		customer.setRentals(listWithMockedRentals);

		// then
		assertTrue(listWithMockedRentals.equals(customer.getRentals()),
				"List with rentals passed as parameter to set list should equal list after setting.");

	}
	
	@Test
	public void setRentalsShouldThrowExceptionWhenPassedListIsEmpty() {
		// given setup() method

		// when
		List<Rental> listWithMockedRentals = new LinkedList<Rental>();

		// then
		assertThrows(IllegalArgumentException.class, () -> customer.setRentals(listWithMockedRentals));

	}
	
	@Test
	public void setRentalsShouldThrowExceptionWhenPassedListIsNull() {
		// given setup() method

		// when
		List<Rental> listWithMockedRentals = null;

		// then
		assertThrows(IllegalArgumentException.class, () -> customer.setRentals(listWithMockedRentals));

	}

	@Test
	public void customerTypeShouldBeSetAccordingToSetValue() {
		// given setup() method

		// when
		customer.setCustomerType(CustomerType.PREMIUM);

		// then
		assertEquals(CustomerType.PREMIUM, customer.getCustomerType(), "customerType has to be set to 'Premium'.");
	}

}
