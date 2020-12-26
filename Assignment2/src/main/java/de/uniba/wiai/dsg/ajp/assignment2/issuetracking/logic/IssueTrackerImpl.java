package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;

public class IssueTrackerImpl implements IssueTracker {

	public IssueTrackerImpl() {
		/*
		 * DO NOT CHANGE - required for grading!
		 */
	}

	@Override
	public void validate(String path) throws IssueTrackingException {

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;

		try {
			schema = sf.newSchema(Path.of("schema1.xsd").toFile());
		} catch (SAXException e) {
			throw new IssueTrackingException("Error while parsing/creating schema.");
		}

		Validator validator;
		validator = schema.newValidator();

		try {
			validator.validate(new StreamSource(Path.of(path).toFile()));
		} catch (SAXException e) {
			throw new IssueTrackingException("XML file is invalid.");
		} catch (IOException e) {
			throw new IssueTrackingException("Error while processing the XML file.");
		}
	}

	public void checkPath(String path) throws IssueTrackingException {
		try {
			Path filePath = Path.of(path);
			if (Files.isDirectory(filePath)) {
				throw new IssueTrackingException(
						"Error while loading file: indicated path is a directory and no file.");
			}
			File file = filePath.toFile();
			if (!file.exists()) {
				throw new IssueTrackingException("Error while loading file: file does not exist.");
			}
		} catch (InvalidPathException e) {
			throw new IssueTrackingException("Error while loading file: path is invalid.");
		}
	}

	@Override
	public ProjectService load(String path) throws IssueTrackingException {

		try {
			checkPath(path);
			validate(path);
		} catch (IssueTrackingException e) {
			throw e;
		}

		Project project;

		try {
			project = (Project) ContextHandler.getUnmarshaller().unmarshal(Path.of(path).toFile());
		} catch (IssueTrackingException e) {
			throw e;
		} catch (JAXBException e) {
			throw new IssueTrackingException("Error while unmarshalling XML file.");
		}

		ProjectService projectService = new ProjectServiceImpl(project);

		return projectService;
	}

	@Override
	public ProjectService create() {
		return new ProjectServiceImpl();
	}

}
