package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;

public class ProjectMenu {

	private static final int ADD_MILESTONE = 1;
	private static final int REMOVE_MILESTONE = 2;
	private static final int LIST_MILESTONES = 3;
	private static final int ADD_ISSUE = 4;
	private static final int CLOSE_ISSUE = 5;
	private static final int REMOVE_ISSUE = 6;
	private static final int LIST_ISSUES = 7;
	private static final int PRINT_TO_CONSOLE = 8;
	private static final int SAVE_TO_FILE = 9;
	private static final int BACK_TO_MAIN_MENU = 0;

	private final ProjectService projectService;
	private final ExtendedConsoleHelper consoleHelper;
	private final AddIssueAction addIssueAction;
	private final AddMilestoneAction addMilestoneAction;

	public ProjectMenu(ProjectService projectService, ExtendedConsoleHelper consoleHelper) {
		super();
		this.projectService = projectService;
		this.consoleHelper = consoleHelper;
		this.addIssueAction = new AddIssueAction(projectService, consoleHelper);
		this.addMilestoneAction = new AddMilestoneAction(projectService, consoleHelper);
	}

	public void open() {
		boolean insideMenu = true;
		while (insideMenu) {
			printProjectMenu();
			try {
				int option = consoleHelper.askIntegerInRange("Please enter your choice", 0, 9);
				switch (option) {
				case ADD_MILESTONE:
					this.addMilestoneAction.addMilestone();
					break;
				case REMOVE_MILESTONE:
					removeMilestone();
					break;
				case LIST_MILESTONES:
					printMilestones();
					break;
				case ADD_ISSUE:
					this.addIssueAction.addIssue();
					break;
				case CLOSE_ISSUE:
					closeIssue();
					break;
				case REMOVE_ISSUE:
					removeIssue();
					break;
				case LIST_ISSUES:
					printIssues();
					break;
				case PRINT_TO_CONSOLE:
					printProject();
					break;
				case SAVE_TO_FILE:
					saveToFile();
					break;
				case BACK_TO_MAIN_MENU:
					insideMenu = false;
					break;
				}
			} catch (IssueTrackingException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				insideMenu = false;
			}
		}

	}

	private void printProjectMenu() {
		consoleHelper.getOut().format(" (%d) Add Milestone %n", ADD_MILESTONE);
		consoleHelper.getOut().format(" (%d) Remove Milestone and Cleanup %n", REMOVE_MILESTONE);
		consoleHelper.getOut().format(" (%d) List Milestones %n", LIST_MILESTONES);
		consoleHelper.getOut().format(" (%d) Add Issue %n", ADD_ISSUE);
		consoleHelper.getOut().format(" (%d) Close Issue %n", CLOSE_ISSUE);
		consoleHelper.getOut().format(" (%d) Remove Issue and Cleanup %n", REMOVE_ISSUE);
		consoleHelper.getOut().format(" (%d) List Issues %n", LIST_ISSUES);
		consoleHelper.getOut().format(" (%d) Print XML on Console %n", PRINT_TO_CONSOLE);
		consoleHelper.getOut().format(" (%d) Save XML to File %n", SAVE_TO_FILE);
		consoleHelper.getOut().format(" (%d) Back to main menu / close without saving %n", BACK_TO_MAIN_MENU);
	}

	private void removeMilestone() throws IssueTrackingException, IOException {
		projectService.removeMilestoneById(consoleHelper.askNonEmptyString("Id of milestone to remove:"));
	}

	private void printMilestones() {
		consoleHelper.printListOfEntities(projectService.getMilestones());
	}

	private void closeIssue() throws IssueTrackingException, IOException {
		projectService.closeIssueById(consoleHelper.askNonEmptyString("Id of issue to close:"));
	}

	private void removeIssue() throws IssueTrackingException, IOException {
		projectService.removeIssueById(consoleHelper.askNonEmptyString("Id of issue to remove:"));
	}

	private void printIssues() {
		consoleHelper.printListOfEntities(projectService.getIssues());
	}

	private void printProject() throws IssueTrackingException {
		projectService.printXMLToConsole();
	}

	private void saveToFile() throws IssueTrackingException, IOException {
		projectService.saveXMLToFile(consoleHelper.askNonEmptyString("File to save issue tracking system:"));
	}

}
