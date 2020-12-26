package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.controller;

import java.util.List;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.model.IssueTrackerModel;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.CreateIssueView;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.MainStage;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Type;

public class IssueController {
	private IssueTrackerModel issueTrackerModel;
	private CreateIssueView createIssueView;
	
	public void startUp(IssueTrackerModel issueTrackerModel, MainStage mainStage) {
		this.issueTrackerModel = issueTrackerModel;
		List<Issue> issues = issueTrackerModel.getIssues();
		this.createIssueView = new CreateIssueView(this, issues, mainStage);
		this.createIssueView.show();
	}
	
	public void addIssue(String id, String name, String description, Severity severity, Type type,
			Set<String> issueIds) throws IssueTrackingException  {
		issueTrackerModel.addIssue(id, name, description, severity, type, issueIds);
	}

}
