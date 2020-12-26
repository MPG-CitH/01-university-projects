package de.uniba.wiai.dsg.ajp.assignment2.issuetracking;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;

public interface IssueTracker {

	/**
	 * Validates the XML file identified by <code>path</code> with an XML Schema
	 * for {@link Project}
	 * 
	 * @param path
	 *            the path to the XML file to be validated
	 * @throws IssueTrackingException
	 *             if the file identified by <code>path</code> is not valid
	 */
	void validate(String path) throws IssueTrackingException;

	/**
	 * Loads an XML file containing a {@link Project} by unmarshalling it into
	 * memory and validating the content by an XML Schema
	 * 
	 * @param path
	 *            the path of the XML file to be unmarshalled
	 * @return a service handle for manipulating the {@link Project}
	 * @throws IssueTrackingException
	 */
	ProjectService load(String path) throws IssueTrackingException;

	/**
	 * Creates a new and empty Project
	 * 
	 * @return a service handle for manipulating the {@link Project}.
	 */
	ProjectService create();

}
