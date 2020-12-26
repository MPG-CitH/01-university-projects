package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidationException;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationResult;
import de.uniba.wiai.dsg.ajp.assignment1.validation.Warning;

public class ResultToCsv {
	Path resultFile;
	ValidationResult result;

	public ResultToCsv(Path resultFile, ValidationResult result) {
		this.resultFile = resultFile;
		this.result = result;
	}

	public void writeToCsv() throws CodeValidationException {

		try (BufferedWriter writeToCsv = Files.newBufferedWriter(resultFile, StandardCharsets.UTF_8,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			writeToCsv.write("File,LineNumber,Warning");
			writeToCsv.newLine();
			for (Warning w : result.getWarnings()) {
				writeToCsv.write(w.getFileName() + "," + w.getLineNumber() + "," + w.getType());
				writeToCsv.newLine();
			}
		} catch (IOException e) {
			throw new CodeValidationException("Something went wrong during writing the warnings to a csv-file.\n" + e.getMessage());
		}
	}

}
