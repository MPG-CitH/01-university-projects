package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtendedConsoleHelper extends ConsoleHelper {

	public ExtendedConsoleHelper(BufferedReader in, PrintStream out) {
		super(in, out);
	}

	/**
	 * Creates a {@link ExtendedConsoleHelper} using System.in and System.out for
	 * their streams.
	 * 
	 * This is a factory method.
	 * 
	 * Usage
	 * 
	 * <code>
	 * ExtendedConsoleHelper console = ExtendedConsoleHelper.build();
	 * </code>
	 * 
	 * @return the configured {@link ExtendedConsoleHelper}
	 */
	public static ExtendedConsoleHelper build() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return new ExtendedConsoleHelper(reader, System.out);
	}

	/**
	 * Lets the user input multiple values with a single line. The values must be
	 * separated by commas (",")
	 * 
	 * Usage
	 * 
	 * <code>
	 * Set<String> inputs = askForMultipleEntries("Ids:");
	 * </code>
	 * 
	 * @param message
	 *            the message shown to the user
	 * @return a set of strings parsed from the user input by separation through
	 *         commas
	 * @throws IOException
	 *             if an error occurs during reading from or writing to a stream
	 */
	public Set<String> askForMultipleEntries(String message) throws IOException {
		Set<String> entries = new HashSet<>();
		String allEntries = askString(message + System.lineSeparator() + "Separate multiple values with commas");
		if (!allEntries.trim().isEmpty()) {
			entries.addAll(Arrays.asList(allEntries.split(",")));
		}
		return entries;
	}

	/**
	 * Lets the user select a value from a list of values
	 * 
	 * 
	 * @param values
	 *            a list of the values to select from
	 * @return the selected value
	 * @throws IOException
	 */
	public <T> T askValuefromList(List<T> values) throws IOException {
		int count = -1;
		for (T value : values) {
			count++;
			getOut().format(" (%d) %s %n", count, value.toString());
		}

		return values.get(askIntegerInRange("Severity:", 0, count));
	}

	/**
	 * Prints the given values to the output stream, separated by new lines
	 * 
	 * @param values
	 */
	public <T> void printListOfEntities(List<T> values) {
		values.stream().forEach(getOut()::println);
	}

}
