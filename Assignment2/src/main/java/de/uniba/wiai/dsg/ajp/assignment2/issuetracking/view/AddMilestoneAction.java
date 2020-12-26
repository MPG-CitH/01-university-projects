package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view;

import java.io.IOException;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;

public class AddMilestoneAction {

	private final ProjectService projectService;
	private final ExtendedConsoleHelper consoleHelper;

	AddMilestoneAction(ProjectService projectService, ExtendedConsoleHelper consoleHelper) {
		super();
		this.projectService = projectService;
		this.consoleHelper = consoleHelper;
	}

	void addMilestone() throws IOException, IssueTrackingException {
		String id = consoleHelper.askNonEmptyString("Id:");
		String name = consoleHelper.askNonEmptyString("Name:");
		Set<String> issueIds = consoleHelper.askForMultipleEntries("Issue ids (separated by commas or empty):");
		projectService.createMilestone(id, name, issueIds);
	}

}
