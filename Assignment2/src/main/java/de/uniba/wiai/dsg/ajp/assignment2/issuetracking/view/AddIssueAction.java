package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Type;

public class AddIssueAction {

	private final ProjectService projectService;
	private final ExtendedConsoleHelper consoleHelper;

	AddIssueAction(ProjectService projectService, ExtendedConsoleHelper consoleHelper) {
		super();
		this.projectService = projectService;
		this.consoleHelper = consoleHelper;
	}

	void addIssue() throws IOException, IssueTrackingException {
		String id = consoleHelper.askNonEmptyString("Id:");
		String name = consoleHelper.askNonEmptyString("Name:");
		String description = consoleHelper.askString("Description:");

		Severity severity = consoleHelper.askValuefromList(Arrays.asList(Severity.values()));

		Type type = consoleHelper.askValuefromList(Arrays.asList(Type.values()));

		String milestoneId = consoleHelper.askString("Milestone id:");
		Set<String> dependencies = consoleHelper
				.askForMultipleEntries("Depending on issue ids (separated by commas or empty):");

		projectService.createIssue(id, name, description, severity, type, milestoneId, dependencies);
	}

}
