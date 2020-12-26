package de.uniba.wiai.dsg.ajp.assignment1.validation;

/**
 * Marks a single validation warning in a source file
 */
public class Warning {

	/**
	 * the absolute path of the file this warning refers to
	 */
	private final String fileName;

	/**
	 * the line number in which the warning is detected
	 */
	private final int lineNumber;

	/**
	 * the type of warning that occurred
	 */
	private final WarningType type;

	public Warning(String fileName, int lineNumber, WarningType type) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public WarningType getType() {
		return type;
	}

}
