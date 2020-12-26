package de.uniba.wiai.dsg.ajp.assignment2;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.IssueTrackerImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view.MainMenu;

public class Main {

	public static void main(String[] args) {
		IssueTracker issueTrackingSystem = new IssueTrackerImpl();
		new MainMenu(issueTrackingSystem).open();
	}
}
