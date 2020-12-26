package de.uniba.wiai.dsg.ajp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreator {
	private static String HTMLWithRentalList;
	private static String HTMLWithoutRentalList;
	private static String HTMLWithRentalListPremium;
	private static String StatementWithRentalList;
	private static String StatementWithoutRentalList;
	private static String StatementWithRentalListPremium;

	private static String resourceFolder = "src/test/resources";

	public static void setupHTMLStatement() throws IOException {
		HTMLWithRentalList = getStringForComparison("CustomerTestHTMLStatementWithRentals.txt");
		HTMLWithoutRentalList = getStringForComparison("CustomerTestHTMLStatementWithoutRentals.txt");
		HTMLWithRentalListPremium = getStringForComparison("CustomerTestHTMLStatementWithRentalsPremium.txt");
	}

	public static void setupStatement() throws IOException {
		StatementWithRentalList = getStringForComparison("CustomerTestStatementWithRentals.txt");
		StatementWithoutRentalList = getStringForComparison("CustomerTestStatementWithoutRentals.txt");
		StatementWithRentalListPremium = getStringForComparison("CustomerTestStatementWithRentalsPremium.txt");
	}

	private static String getStringForComparison(String filename) throws IOException {
		Path path = Paths.get(resourceFolder, filename);

		String expectedOutput = "";

		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String currentLine = reader.readLine();

		while (currentLine != null) {
			expectedOutput += currentLine + "\n";
			currentLine = reader.readLine();
		}

		reader.close();
		expectedOutput = expectedOutput.trim();
		return expectedOutput;

	}

	public static String getHTMLWithRentalList() {
		return HTMLWithRentalList;
	}

	public static String getHTMLWithoutRentalList() {
		return HTMLWithoutRentalList;
	}

	public static String getHTMLWithRentalListPremium() {
		return HTMLWithRentalListPremium;
	}

	public static String getStatementWithRentalList() {
		return StatementWithRentalList;
	}

	public static String getStatementWithoutRentalList() {
		return StatementWithoutRentalList;
	}

	public static String getStatementWithRentalListPremium() {
		return StatementWithRentalListPremium;
	}

}
