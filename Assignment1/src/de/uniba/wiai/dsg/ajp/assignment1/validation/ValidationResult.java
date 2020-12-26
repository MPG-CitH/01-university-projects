package de.uniba.wiai.dsg.ajp.assignment1.validation;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores the output of a validation run in the form of a list of warnings
 * 
 */
public class ValidationResult {

	private final List<Warning> warnings;

	public ValidationResult() {
		warnings = new LinkedList<Warning>();
	}

	public void addWarning(Warning warning) {
		warnings.add(warning);
	}

	public List<Warning> getWarnings() {
		return warnings;
	}

}
