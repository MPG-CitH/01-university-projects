package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StatementIntegrationTestsWithRentalList {
	private static Customer testCustomer;

	@BeforeAll
	public static void setup() throws Exception {
		testCustomer = TestCustomer.create();
	}

	@AfterAll
	public static void tearDown() throws Exception {
		testCustomer = null;
	}

	@Test
	public void statementNormalSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.NORMAL);
		String originalStatement = FileLoader.loadStatementNormal();

		// when
		String testStatement = testCustomer.statement();

		// then
		assertEquals(originalStatement, testStatement,
				"Statement should be correct according to customer data of a normal customer with movies in rental list.");
	}

	@Test
	public void htmlStatementNormalSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.NORMAL);
		String originalHtmlStatement = FileLoader.loadHtmlStatementNormal();

		// when
		String testHtmlStatement = testCustomer.htmlStatement();

		// then
		assertEquals(originalHtmlStatement, testHtmlStatement,
				"Html statement should be correct according to customer data of a normal customer with movies in rental list.");
	}

	@Test
	public void statementPremiumSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.PREMIUM);
		String originalStatement = FileLoader.loadStatementPremium();

		// when
		String testStatement = testCustomer.statement();

		// then
		assertEquals(originalStatement, testStatement,
				"Statement should be correct according to customer data of a premium customer with movies in rental list.");
	}

	@Test
	public void htmlStatementPremiumSuccessful() throws IOException {
		// given setup() method and
		testCustomer.setCustomerType(CustomerType.PREMIUM);
		String originalHtmlStatement = FileLoader.loadHtmlStatementPremium();

		// when
		String testHtmlStatement = testCustomer.htmlStatement();

		// then
		assertEquals(originalHtmlStatement, testHtmlStatement,
				"Html statement should be correct according to customer data of a premium customer with movies in rental list.");
	}
}
