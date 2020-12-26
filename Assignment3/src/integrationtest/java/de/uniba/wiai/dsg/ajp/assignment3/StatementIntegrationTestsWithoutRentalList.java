package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StatementIntegrationTestsWithoutRentalList {
	private static Customer testCustomer;

	@BeforeAll
	public static void setup() throws Exception {
		testCustomer = new Customer("John Doe");
	}

	@AfterAll
	public static void tearDown() throws Exception {
		testCustomer = null;
	}

	@Test
	public void statementNormalWithoutRentalListSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.NORMAL);
		String originalStatement = FileLoader.loadStatementNormalWithoutRentalList();

		// when
		String testStatement = testCustomer.statement();

		// then
		assertEquals(originalStatement, testStatement,
				"Statement should be correct according to customer data of a normal customer with no movies in rental list.");
	}

	@Test
	public void htmlStatementNormalWithoutRentalListSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.NORMAL);
		String originalHtmlStatement = FileLoader.loadHtmlStatementNormalWithoutRentalList();

		// when
		String testHtmlStatement = testCustomer.htmlStatement();

		// then
		assertEquals(originalHtmlStatement, testHtmlStatement,
				"Html statement should be correct according to customer data of a normal customer with no movies in rental list.");
	}

	@Test
	public void statementPremiumWithoutRentalListSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.PREMIUM);
		String originalStatement = FileLoader.loadStatementPremiumWithoutRentalList();

		// when
		String testStatement = testCustomer.statement();

		// then
		assertEquals(originalStatement, testStatement,
				"Statement should be correct according to customer data of a premium customer with no movies in rental list.");
	}

	@Test
	public void htmlStatementPremiumWithoutRentalListSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.PREMIUM);
		String originalHtmlStatement = FileLoader.loadHtmlStatementPremiumWithoutRentalList();

		// when
		String testHtmlStatement = testCustomer.htmlStatement();

		// then
		assertEquals(originalHtmlStatement, testHtmlStatement,
				"Html statement should be correct according to customer data of a premium customer with no movies in rental list.");
	}
}
