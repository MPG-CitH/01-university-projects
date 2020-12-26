package de.uniba.wiai.dsg.ajp.assignment3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
	private static String dirPath = "src/integrationtest/resources";

	private static String loadFile(String fileName) throws IOException {
		Path path = Paths.get(dirPath, fileName);
		String statement = Files.readString(path, StandardCharsets.UTF_8);
		// remove \r character
		statement = statement.replaceAll("\\r", "");
		return statement;
	}

	public static String loadStatementNormal() throws IOException {
		return loadFile("statementNormal.txt");
	}

	public static String loadHtmlStatementNormal() throws IOException {
		return loadFile("htmlStatementNormal.html");
	}

	public static String loadStatementPremium() throws IOException {
		return loadFile("statementPremium.txt");
	}

	public static String loadHtmlStatementPremium() throws IOException {
		return loadFile("htmlStatementPremium.html");
	}
	
	public static String loadStatementNormalWithoutRentalList() throws IOException {
		return loadFile("statementNormalNoRentalList.txt");
	}
	
	public static String loadHtmlStatementNormalWithoutRentalList() throws IOException {
		return loadFile("htmlStatementNormalNoRentalList.html");
	}
	
	public static String loadStatementPremiumWithoutRentalList() throws IOException {
		return loadFile("statementPremiumNoRentalList.txt");
	}
	
	public static String loadHtmlStatementPremiumWithoutRentalList() throws IOException {
		return loadFile("htmlStatementPremiumNoRentalList.html");
	}

}
