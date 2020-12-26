package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

public class Issue {

	private String id;

	private String name;

	private String description;

	private Severity severity;

	private Type type;

	private State state;

	private Milestone milestone;

	private List<Issue> dependencies = new LinkedList<>();

	public Issue() {

	}

	public Issue(String id, String name, String description, Severity severity, Type type, State state,
			Milestone milestone, List<Issue> dependencies) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.severity = severity;
		this.type = type;
		this.state = state;
		this.milestone = milestone;
		this.dependencies = dependencies;
	}


	@XmlElement(name = "id", required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "name", required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "description", required = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(name = "severity", required = true)
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	@XmlAttribute(name = "type", required = true)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlAttribute(name = "state", required = true)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@XmlElement(name = "milestone", required = false)
	@XmlIDREF
	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	@XmlElement(name = "dependencies", required = false)
	@XmlIDREF
	public List<Issue> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Issue> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String toString() {
		String issueNames = "";
		for (Issue dependentIssue : dependencies) {
			issueNames += dependentIssue.getName() + " ";
		}
		String milestone = "";
		if (this.milestone != null) {
			milestone = this.milestone.getName();
		}

		return "Issue [id=" + id + ", name=" + name + ", description=" + description + ", severity=" + severity
				+ ", type=" + type + ", state=" + state + ", milestone=" + milestone + ", dependsUponIssues=( "
				+ issueNames + ")]";
	}

}
