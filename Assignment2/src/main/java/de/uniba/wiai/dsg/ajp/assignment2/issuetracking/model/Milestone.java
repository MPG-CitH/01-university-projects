package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

public class Milestone {

	public Milestone() {
	}

	public Milestone(String id, String name, List<Issue> issues) {
		this.id = id;
		this.name = name;
		this.issues = issues;
	}

	private String id;

	private String name;

	private List<Issue> issues = new LinkedList<>();

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

	@XmlElement(name = "due", required = false)
	@XmlIDREF
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		String issueNames = "";
		for (Issue issue : issues) {
			issueNames += issue.getName() + " ";
		}
		return "Milestone [id=" + id + ", name=" + name + ", issues=( " + issueNames + ")]";
	}
}
