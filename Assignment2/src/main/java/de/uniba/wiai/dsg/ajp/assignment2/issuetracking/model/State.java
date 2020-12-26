package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum State {
	@XmlEnumValue("open") OPEN, 
	@XmlEnumValue("closed") CLOSED;
}
