package de.uniba.wiai.dsg.ajp.assignment1;

import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidationException;
import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidator;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationTask;
import de.uniba.wiai.dsg.ajp.assignment1.validation.impl.SimpleCodeValidator;

public class Main {

	public static void main(String[] args) throws CodeValidationException {
		validate(args);
		ValidationTask task = toValidationTask(args);
		print(task);
		checkForWarnings(task);
	}

	private static void validate(String[] args) {
		if (args.length != 2) {
			printUsage();
			throw new IllegalArgumentException("expected 2 arguments, given " + args.length);
		}
	}

	private static void printUsage() {
		System.out.println("Usage: ");
		System.out.println("ROOT_FOLDER RESULT_FILE");
	}

	private static ValidationTask toValidationTask(String[] args) {
		return new ValidationTask(args[0], args[1]);
	}

	private static void print(ValidationTask task) {
		System.out.println("Processing the given input as follows:");
		System.out.println("Root Folder: " + task.getRootFolder());
		System.out.println("Result file: " + task.getResultFile());
		System.out.println();
	}

	private static void checkForWarnings(ValidationTask task) throws CodeValidationException {
		CodeValidator validator = new SimpleCodeValidator();

		try {
			validator.validate(task);
			System.out.println("\nRUN SUCCESSFUL");
		} catch (CodeValidationException e) {
			System.out.println("\nRUN FAILED");
			throw e;
		}
	}

}
