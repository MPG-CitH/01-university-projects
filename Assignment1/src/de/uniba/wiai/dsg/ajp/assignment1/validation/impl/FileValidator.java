package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import de.uniba.wiai.dsg.ajp.assignment1.validation.FileValidatorReturn;
import de.uniba.wiai.dsg.ajp.assignment1.validation.Warning;
import de.uniba.wiai.dsg.ajp.assignment1.validation.WarningType;

public class FileValidator implements Callable<FileValidatorReturn> {
	Path sourceFile;
	List<Warning> guidelineViolations;

	public FileValidator(Path sourceFile) {
		this.sourceFile = sourceFile;
		this.guidelineViolations = new ArrayList<Warning>();
	}

	@Override
	public FileValidatorReturn call() {

		try (BufferedReader reader = Files.newBufferedReader(sourceFile)) {

			String line = reader.readLine();

			int tabCounter = 0;
			int lineCounter = 1;

			while (line != null) {
				if (!line.equals("")) {
					if (Character.isWhitespace(line.charAt(line.length() - 1))) {
						Warning warning = new Warning(sourceFile.toString(), lineCounter,
								WarningType.TRAILING_WHITESPACES);
						guidelineViolations.add(warning);
						System.out.println(String.format("%s, %s, %s", warning.getFileName(), warning.getLineNumber(), warning.getType()));
					}

					String trimmedLine = line.trim();
					String ending = "\\S+.*";

					if (trimmedLine.startsWith("}") && tabCounter > 0) {
						tabCounter--;
					} else if (trimmedLine.startsWith("*")) {
						ending = "\\s\\*.*";
					}

					if (!line.matches("\\t".repeat(tabCounter) + ending)) {
						Warning warning = new Warning(sourceFile.toString(), lineCounter,
								WarningType.FAULTY_INDENTATION);
						guidelineViolations.add(warning);
						System.out.println(String.format("%s, %s, %s", warning.getFileName(), warning.getLineNumber(), warning.getType()));
					}

					if (trimmedLine.endsWith("{")) {
						tabCounter++;
					}
				}

				line = reader.readLine();
				lineCounter++;
			}
		} catch (IOException e) {
			System.err.println("The file " + sourceFile + " was not found.");
		}

		return new FileValidatorReturn(sourceFile, guidelineViolations);
	}

}
