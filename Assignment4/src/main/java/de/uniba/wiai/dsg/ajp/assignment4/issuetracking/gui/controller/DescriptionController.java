package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.controller;


import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.DescriptionView;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.MainStage;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.model.Issue;


public class DescriptionController {
	private DescriptionView descriptionView;
	
	public void startUp(Issue issue, MainStage mainStage) {
		this.descriptionView = new DescriptionView(issue, mainStage);
		this.descriptionView.show();
	}
	

}
