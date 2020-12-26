package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.controller;

import java.io.File;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.model.IssueTrackerModel;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.MainStage;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Issue;

public class MainController {
	MainStage mainStage;
	IssueTrackerModel issueTrackerModel;

	public void startUp() {
		MainStage stage = new MainStage(this);
		this.mainStage = stage;
		issueTrackerModel = new IssueTrackerModel();
		try {
			issueTrackerModel.create();
			mainStage.titleProperty().bind(issueTrackerModel.titleProperty());
			// set observable list as TableView data model
			// View will update on list change
			mainStage.getIssueView().setItems(issueTrackerModel.getIssueObservable());
		} catch (IssueTrackingException e) {
			mainStage.handleStartupError(e.getMessage());
		}
		mainStage.show();
	}

	public void loadProjectMenuSelection(File file) throws IssueTrackingException {
		issueTrackerModel.loadProject(file);
	}

	public void saveProjectMenuSelection(File file) throws IssueTrackingException {
		issueTrackerModel.saveProject(file);
	}

	public boolean isProjectEmpty() {
		return issueTrackerModel.isProjectEmpty();
	}

	public void newProjectMenuSelection() {
		issueTrackerModel.createProject();
	}

	public void deleteIssue(String id) throws IssueTrackingException {
		issueTrackerModel.deleteIssue(id);
	}

	public void closeIssue(String id) throws IssueTrackingException {
		issueTrackerModel.closeIssue(id);
	}

	public void openDescriptionView(Issue issue) {
		DescriptionController descriptionController = new DescriptionController();
		descriptionController.startUp(issue, mainStage);
	}

	public void openCreateIssueView() {
		IssueController issueController = new IssueController();
		issueController.startUp(issueTrackerModel, mainStage);
	}
}
