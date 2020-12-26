package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Severity {
	@XmlEnumValue("trivial") TRIVIAL, 
	@XmlEnumValue("minor") MINOR, 
	@XmlEnumValue("major") MAJOR, 
	@XmlEnumValue("critical") CRITICAL;
}
