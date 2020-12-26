package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.model;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl.IssueTrackerImpl;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Type;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IssueTrackerModel {
	private IssueTracker issueTracker;
	private ProjectService projectService;
	private StringProperty sourceTitle;
	private ObservableList<Issue> issues;
	private String currentTitle;

	public void create() throws IssueTrackingException {
		sourceTitle = new SimpleStringProperty();
		issues = FXCollections.observableArrayList();
		currentTitle = "";
		issueTracker = new IssueTrackerImpl();
		createProject();
	}

	public ObservableList<Issue> getIssueObservable() {
		return issues;
	}

	public StringProperty titleProperty() {
		return sourceTitle;
	}

	public void loadProject(File file) throws IssueTrackingException {
		if (file != null) {
			this.projectService = issueTracker.load(file.getAbsolutePath());
			this.issues.setAll(projectService.getIssues());
			this.sourceTitle.set(file.getAbsolutePath());
			this.updateTitleStringProperty(file.getAbsolutePath(), issues.size());
		}
	}

	public boolean isProjectEmpty() {
		return this.projectService.getIssues().isEmpty();
	}

	public void saveProject(File file) throws IssueTrackingException {
		if (file != null) {
			String path = file.getAbsolutePath();
			projectService.saveXMLToFile(path);
			updateTitleStringProperty(path, projectService.getIssues().size());
		}
	}

	public void createProject() {
		this.projectService = issueTracker.create();
		this.updateTitleStringProperty("New Issue Tracking Project", -1);
		issues.setAll(Collections.emptyList());
	}

	private void updateTitleStringProperty(String path, int c) {
		String title = path;
		currentTitle = path;

		String count = Integer.toString(c);

		if (c >= 0) {
			title += " - " + count + getIssueEnding(c);
		}

		this.sourceTitle.set(title);
	}

	private void updateNumberOfIssues(int c) {
		String title = currentTitle;
		String count = Integer.toString(c);

		if (c >= 0) {
			title += " - " + count + getIssueEnding(c);
		}

		this.sourceTitle.set(title);
	}

	private String getIssueEnding(int c) {
		if (c == 0 || c > 1) {
			return " Issues";
		} else {
			return " Issue";
		}
	}

	public List<Issue> getIssues() {
		return this.projectService.getIssues();
	}

	public void deleteIssue(String id) throws IssueTrackingException {
		projectService.removeIssueById(id);
		issues.setAll(projectService.getIssues());
		updateNumberOfIssues(issues.size());
	}

	public void closeIssue(String id) throws IssueTrackingException {
		projectService.closeIssueById(id);
		issues.setAll(projectService.getIssues());
	}

	public void addIssue(String id, String name, String description, Severity severity, Type type, Set<String> issueIds)
			throws IssueTrackingException {
		projectService.createIssue(id, name, description, severity, type, issueIds);
		issues.setAll(projectService.getIssues());
		updateNumberOfIssues(issues.size());
	}
}
