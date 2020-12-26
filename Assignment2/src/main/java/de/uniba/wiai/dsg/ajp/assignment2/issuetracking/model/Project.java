package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private List<Issue> issues = new LinkedList<>();
	
	private List<Milestone> milestones = new LinkedList<>();

	@XmlElement(name="issue", required=false)
	public List<Issue> getIssues() {
		return issues;
	}
	
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	@XmlElement(name="milestone", required=false)
	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
	
	public boolean isUniqueMilestoneId(String id) {
		return !this.milestones.stream().map(m -> m.getId()).collect(Collectors.toList()).contains(id);
	}
	
	public boolean isUniqueIssueId(String id) {
		return !this.issues.stream().map(m -> m.getId()).collect(Collectors.toList()).contains(id);
	}
	

}
