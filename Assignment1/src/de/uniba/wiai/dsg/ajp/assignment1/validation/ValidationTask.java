package de.uniba.wiai.dsg.ajp.assignment1.validation;

/**
 * Groups together all parameters for a validation task and does not contain any
 * logic.
 */
public class ValidationTask {

	/**
	 * the root folder from which the validation is started (the root folder of
	 * a coding project)
	 */
	private final String rootFolder;

	/**
	 * the path for the validation result output
	 */
	private final String resultFile;

	public ValidationTask(String rootFolder, String resultFile) {
		this.rootFolder = rootFolder;
		this.resultFile = resultFile;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public String getResultFile() {
		return resultFile;
	}

}
