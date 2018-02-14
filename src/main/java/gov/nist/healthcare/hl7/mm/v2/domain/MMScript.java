package gov.nist.healthcare.hl7.mm.v2.domain;

import java.util.ArrayList;
import java.util.List;

import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;


public class MMScript {
	
	private List<Issue> syntax = new ArrayList<Issue>();
	private List<Command> commands;

	public MMScript(List<Command> commands) {
		super();
		this.commands = commands;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public List<Issue> getSyntax() {
		return syntax;
	}

	public void setSyntax(List<Issue> syntax) {
		this.syntax = syntax;
	}
	
	
}
