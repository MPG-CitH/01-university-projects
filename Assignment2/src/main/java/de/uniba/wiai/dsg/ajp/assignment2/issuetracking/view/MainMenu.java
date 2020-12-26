package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;

public class MainMenu {

	private static final int LOAD = 1;
	private static final int NEW = 2;
	private static final int EXIT = 0;

	private final IssueTracker issueTracker;
	private final ExtendedConsoleHelper consoleHelper;

	public MainMenu(IssueTracker issueTracker) {
		super();
		this.issueTracker = issueTracker;
		this.consoleHelper = ExtendedConsoleHelper.build();
	}

	public void open() {
		boolean insideMenu = true;
		while (insideMenu) {
			printMainMenu();
			try {
				int option = askOption();
				switch (option) {
				case LOAD:
					String pathToLoad = consoleHelper.askNonEmptyString("Please enter the path of the file to load: ");
					new ProjectMenu(issueTracker.load(pathToLoad), consoleHelper).open();
					break;
				case NEW:
					new ProjectMenu(issueTracker.create(), consoleHelper).open();
					break;
				case EXIT:
					consoleHelper.getOut().println("Exiting ...");
					insideMenu = false;
					break;
				}
			} catch (IssueTrackingException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println("Exiting because of an error: " + e.getMessage());
				insideMenu = false;
			}
		}
	}

	private void printMainMenu() {
		consoleHelper.getOut().format(" (%d) Validate and Load Project %n", LOAD);
		consoleHelper.getOut().format(" (%d) Create New Project %n", NEW);
		consoleHelper.getOut().format(" (%d) Exit %n", EXIT);
	}

	private int askOption() throws IOException {
		return consoleHelper.askIntegerInRange("Please enter option", 0, 2);
	}

}
