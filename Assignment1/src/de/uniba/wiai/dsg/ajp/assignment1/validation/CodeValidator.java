package de.uniba.wiai.dsg.ajp.assignment1.validation;


/**
 * The interface to be implemented
 */
public interface CodeValidator {

	ValidationResult validate(ValidationTask task) throws CodeValidationException;

}
