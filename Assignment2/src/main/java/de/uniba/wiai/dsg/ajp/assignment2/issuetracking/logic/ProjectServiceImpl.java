package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Issue;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Milestone;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.State;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Type;

public class ProjectServiceImpl implements ProjectService {
	private Project project;

	public ProjectServiceImpl() {
		this.project = new Project();
	}

	public ProjectServiceImpl(Project project) {
		this.project = project;
	}

	@Override
	public void createMilestone(String id, String name, Set<String> issueIds) throws IssueTrackingException {
		if (ValidationHelper.isId(id) && hasValue(name) && issueIds != null) { // validate parameters
			if (project.isUniqueMilestoneId(id)) {
				boolean issueMilestoneConflict = false;
				List<Issue> issues = Collections.<Issue>emptyList();
				if (issueIds.size() > 0) {
					issues = project.getIssues() // get issue references for issueIds
							.stream().filter(i -> issueIds.contains(i.getId())).collect(Collectors.toList());
					if (issues.size() != issueIds.size()) {
						throw new IssueTrackingException(
								"Could not create milestone: referenced issue does not exist.");
					}
					issueMilestoneConflict = issues.stream() // check if any issue from issueID already references a
																// milestone
							.anyMatch(i -> i.getMilestone() != null);
				}
				if (!issueMilestoneConflict) {
					List<Milestone> milestones = new LinkedList<Milestone>(); // create deep copy and add new milestone
					milestones.addAll(this.project.getMilestones());
					milestones.add(new Milestone(id, name, issues));
					project.setMilestones(milestones);
				} else {
					throw new IssueTrackingException(
							"Could not create milestone: issue from given list already references a milestone.");
				}

			} else {
				throw new IssueTrackingException("Could not create milestone: milestone id already exists in project.");
			}
		} else {
			throw new IssueTrackingException("Could not create milestone: passed parameter is invalid.");
		}

	}

	@Override
	public List<Milestone> getMilestones() {
		return project.getMilestones();
	}

	public Milestone getMilestoneById(String id) {
		return project.getMilestones().stream().filter(m -> id.equals(m.getId())).findFirst().orElse(null);
	}

	@Override
	public void removeMilestoneById(String id) throws IssueTrackingException {
		if (ValidationHelper.isId(id)) {
			Milestone m = getMilestoneById(id);
			if (m != null) {
				List<Milestone> milestones = project.getMilestones();
				milestones.remove(m);
				for (Issue i : project.getIssues()) {
					Milestone issueMilestone = i.getMilestone();
					if (issueMilestone != null) {
						if (issueMilestone.getId().equals(m.getId())) {
							i.setMilestone(null);
						}
					}
				}
			} else {
				throw new IssueTrackingException("Could not remove milestone: no milestone by passed id.");
			}
		} else {
			throw new IssueTrackingException("Could not remove milestone: passed id is invalid.");
		}

	}

	@Override
	public void createIssue(String id, String name, String description, Severity severity, Type type,
			String milestoneId, Set<String> dependencies) throws IssueTrackingException {
		if (ValidationHelper.isId(id) && hasValue(name) && description != null && milestoneId != null
				&& dependencies != null) {
			if (project.isUniqueIssueId(id)) {
				boolean invalidDependencies = dependencies.stream()
						.anyMatch(d -> getIssueById(d) == null || !ValidationHelper.isId(d));
				if (!invalidDependencies) {
					Issue newIssue;
					List<Issue> issueDependencies = dependencies.stream().map(d -> getIssueById(d))
							.collect(Collectors.toList());
					Milestone milestone = null;
					if (!milestoneId.isEmpty()) {
						milestone = getMilestoneById(milestoneId);
						if (milestone == null) {
							throw new IssueTrackingException("Could not create issue: milestone is invalid.");
						}
					}
					newIssue = new Issue(id, name, description, severity, type, State.OPEN, milestone,
							issueDependencies);
					List<Issue> issues = new LinkedList<Issue>(); // create deep copy and add new issue
					issues.addAll(project.getIssues());
					issues.add(newIssue);
					project.setIssues(issues);
					if (milestone != null) {
						List<Issue> milestoneIssues = new LinkedList<Issue>(); // create deep copy of issues of
																				// milestone and add new issue
						milestoneIssues.addAll(milestone.getIssues());
						milestoneIssues.add(newIssue);
						milestone.setIssues(milestoneIssues);
					}
				} else {
					throw new IssueTrackingException("Could not create issue: passed dependency is invalid.");
				}
			} else {
				throw new IssueTrackingException("Could not create issue: passed id already exists.");
			}
		} else {
			throw new IssueTrackingException("Could not create issue: passed parameters are invalid.");
		}

	}

	@Override
	public List<Issue> getIssues() {
		return project.getIssues();
	}

	public List<String> getIssueIds() {
		return this.getIssues().stream().map(i -> i.getId()).collect(Collectors.toList());
	}

	public Issue getIssueById(String id) {
		return project.getIssues().stream().filter(i -> id.equals(i.getId())).findFirst().orElse(null);
	}

	@Override
	public void removeIssueById(String id) throws IssueTrackingException {
		if (ValidationHelper.isId(id)) {
			Issue i = getIssueById(id);
			if (i == null) {
				throw new IssueTrackingException("Could not remove issue: no issue by passed id.");
			}
			removeIssueFromIssue(i);
			removeIssueFromMilestones(i);
			project.getIssues().remove(i);
		} else {
			throw new IssueTrackingException("Could not remove issue: passed id is invalid.");
		}

	}

	private void removeIssueFromIssue(Issue dependency) {
		for (Issue i : project.getIssues()) {
			List<Issue> dependants = i.getDependencies();
			Issue remove = null;
			for (Issue dependant : dependants) {
				if (dependant.getId().equals(dependency.getId())) {
					remove = dependency;
					break;
				}
			}
			dependants.remove(remove);
		}
	}

	private void removeIssueFromMilestones(Issue removeIssue) {
		for (Milestone m : project.getMilestones()) {
			List<Issue> milestoneIssues = m.getIssues();
			for (Issue issue : milestoneIssues) {
				if (issue.getId().equals(removeIssue.getId())) {
					break;
				}
			}
			milestoneIssues.remove(removeIssue);
		}
	}

	@Override
	public void closeIssueById(String id) throws IssueTrackingException {
		if (ValidationHelper.isId(id)) {
			Issue i = getIssueById(id);
			if (i == null) {
				throw new IssueTrackingException("Could not close issue: no issue by passed id.");
			}
			if (i.getState() == State.OPEN) {
				for (Issue dependentIssue : i.getDependencies()) {
					if (State.OPEN == dependentIssue.getState()) {
						throw new IssueTrackingException("Could not close issue: dependent issue with state 'open'.");
					}
				}
				i.setState(State.CLOSED);
			} else {
				throw new IssueTrackingException("Could not close issue: state of issue is already 'closed'.");
			}
		} else {
			throw new IssueTrackingException("Could not close issue: passed id is invalid.");
		}

	}

	@Override
	public void printXMLToConsole() throws IssueTrackingException {
		try {
			ContextHandler.getMarshaller().marshal(project, System.out);
		} catch (IssueTrackingException e) {
			throw e;
		} catch (JAXBException e) {
			throw new IssueTrackingException("Error while marshalling to XML.");
		}
	}

	@Override
	public void saveXMLToFile(String path) throws IssueTrackingException {
		try {
			Path filePath = Path.of(path);

			if (Files.isDirectory(filePath)) {
				throw new IssueTrackingException(
						"Error while saving XML to file: indicated path is a directory and no file.");
			}

			try {
				ContextHandler.getMarshaller().marshal(project, filePath.toFile());
			} catch (IssueTrackingException e) {
				throw e;
			} catch (JAXBException e) {
				throw new IssueTrackingException("Error while marshalling to XML file.");
			}

		} catch (InvalidPathException e) {
			throw new IssueTrackingException("Error while saving XML to file: path is invalid.");

		}
	}

	private boolean hasValue(String s) {
		return s != "" && s != null;
	}

}
