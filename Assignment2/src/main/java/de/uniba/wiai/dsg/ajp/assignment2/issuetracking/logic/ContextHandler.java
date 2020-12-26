package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;

public class ContextHandler {
	private static JAXBContext context;
	private static Marshaller marshaller;
	private static Unmarshaller unmarshaller;

	public static Marshaller getMarshaller() throws IssueTrackingException {
		setup();
		return marshaller;
	}

	public static Unmarshaller getUnmarshaller() throws IssueTrackingException {
		setup();
		return unmarshaller;
	}

	private static void setup() throws IssueTrackingException {
		if (context == null) {
			try {
				context = JAXBContext.newInstance(Project.class);
				unmarshaller = context.createUnmarshaller();
				marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			} catch (JAXBException e) {
				throw new IssueTrackingException("Error while creating JAXBContext or related objects.");
			}
		}
	}

}
