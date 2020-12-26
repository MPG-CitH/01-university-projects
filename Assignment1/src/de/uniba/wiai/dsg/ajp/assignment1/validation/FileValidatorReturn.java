package de.uniba.wiai.dsg.ajp.assignment1.validation;

import java.nio.file.Path;
import java.util.List;

public class FileValidatorReturn {
	Path sourceFile;
	List<Warning> guidelineViolations;

	public FileValidatorReturn(Path sourceFile, List<Warning> guidelineViolations) {
		this.sourceFile = sourceFile;
		this.guidelineViolations = guidelineViolations;
	}

	public Path getSourceFile() {
		return sourceFile;
	}

	public List<Warning> getGuidelineViolations() {
		return guidelineViolations;
	}

}
