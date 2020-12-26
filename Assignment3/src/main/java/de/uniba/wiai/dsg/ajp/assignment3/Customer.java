package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a customer of the video store.
 * 
 * A customer has a name and a list of film rentals. The customer can be normal
 * or premium customer.
 */
public class Customer {

	private String name;

	private CustomerType customerType;

	private List<Rental> rentals = new LinkedList<Rental>();

	/**
	 * Constructor that initializes the {@code Customer} with a given name.
	 * <br>
	 * Postcondition: 
	 * <ul>
	 * <li>{@code getName() == name}</li>
	 * </ul>
	 * @param name
	 * 		the initial value for the name field of {@code Customer}
	 */
	public Customer(String name) {
		super();
		if (name != null && name != "") {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Error. name must not be null or empty.");
		}

		this.customerType = CustomerType.NORMAL;
	}

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a customer.
	 * 
	 * @param name The name to be set. Must not be <code>null</code> or empty.
	 * 
	 * @throws IllegalArgumentException if the name is invalid.
	 */
	public void setName(String name) {
		if (name != null && name != "") {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Error. 'name' must not be null or empty.");
		}
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	/**
	 * Sets a customer type.
	 * 
	 * @param customerType The customer type to be set. Must not be
	 *                     <code>null</code>.
	 * 
	 * @throws IllegalArgumentException if the customer type is invalid.
	 */
	public void setCustomerType(CustomerType customerType) {
		if (customerType != null) {
			this.customerType = customerType;
		} else {
			throw new IllegalArgumentException("Error. 'customerType' must not be null.");
		}
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	/**
	 * Sets a list of film rentals to a customer.
	 * 
	 * <p>
	 * Precondition:
	 * <ul>
	 * <li>The list must not be empty.</li>
	 * <li>The list must not be <code>null</code>.</li>
	 * </ul>
	 * 
	 * <p>
	 * Postcondition:
	 * <ul>
	 * <li>The list will be set to a customer.</li>
	 * </ul>
	 * 
	 * @param rentals The list of all film rentals from a customer.
	 * 
	 * @throws IllegalArgumentException if the list is invalid.
	 */
	public void setRentals(List<Rental> rentals) {
		if (rentals != null && rentals.size() != 0) {
			this.rentals = rentals;
		} else {
			throw new IllegalArgumentException("Error. 'rentals' must not be null or empty.");
		}
	}

	/**
	 * Gives a statement about the rental record of frequent renter points of a
	 * customer.
	 * 
	 * @return the calculated rental record of a customer.
	 */
	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		for (Rental each : this.rentals) {
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t(" + String.valueOf(each.getMovie().getPictureQuality())
					+ ")\t" + String.valueOf(each.getCharge()) + "\n";
		}

		// fetch total charge
		double totalCharge = getTotalCharge();

		// handle premium customer discount
		if (customerType == CustomerType.PREMIUM) {
			// calculate discount and discounted total charge, round numbers
			double discount = Math.round((totalCharge * 0.1) * 100) / 100.0;
			totalCharge = Math.round((totalCharge * 0.9) * 100) / 100.0;

			// add footer line for discount
			result += "10% Premium Customer Discount\t-" + String.valueOf(discount) + "\n";
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(totalCharge) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	/**
	 * Gives a HTML supported statement about the rental record of frequent renter
	 * points from a customer.
	 * 
	 * @return the calculated rental record from a customer.
	 */
	public String htmlStatement() {
		String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

		for (Rental each : rentals) {
			// show figures for each rental
			result += each.getMovie().getTitle() + " (" + String.valueOf(each.getMovie().getPictureQuality()) + "): "
					+ String.valueOf(each.getCharge()) + "<BR>\n";
		}

		// fetch total charge
		double totalCharge = getTotalCharge();

		// handle premium customer discount
		if (customerType == CustomerType.PREMIUM) {
			// calculate discount and discounted total charge, round numbers
			double discount = Math.round((totalCharge * 0.1) * 100) / 100.0;
			totalCharge = Math.round((totalCharge * 0.9) * 100) / 100.0;

			// add footer line for discount
			result += "<P>10% Premium Customer Discount: <EM>-" + String.valueOf(discount) + "</EM><P>\n";
		}

		// add footer lines
		result += "<P>You owe <EM>" + String.valueOf(totalCharge) + "</EM><P>\n";
		result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRenterPoints())
				+ "</EM> frequent renter points<P>";
		return result;
	}

	/**
	 * Gets the total charge of all film rentals from a customer.
	 * 
	 * @return the amount of the total charge.
	 */
	double getTotalCharge() {
		double result = 0;

		for (Rental each : rentals) {
			result += each.getCharge();
		}

		return result;
	}

	/**
	 * Gets the total of frequent renter points of all film rentals from a customer.
	 * 
	 * @return the calculated total number of the frequent renter points.
	 */
	int getTotalFrequentRenterPoints() {
		int result = 0;

		for (Rental rental : rentals) {
			result += rental.getFrequentRenterPoints();
		}

		return result;
	}
}
