package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.util.ArrayList;
import java.util.List;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;

public class ExecutionContext {
	MMScript script;
	String messageOriginal;
	String messageFinal;
	String message;
	ExecutionEnvironment environement;
	int executedCommands = 0;
	List<Issue> issues = new ArrayList<Issue>();
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MMScript getScript() {
		return script;
	}
	public void setScript(MMScript script) {
		this.script = script;
	}

	public String getMessageOriginal() {
		return messageOriginal;
	}
	public void setMessageOriginal(String messageOriginal) {
		this.messageOriginal = messageOriginal;
	}
	public String getMessageFinal() {
		return messageFinal;
	}
	public void setMessageFinal(String messageFinal) {
		this.messageFinal = messageFinal;
	}
	public ExecutionEnvironment getEnvironement() {
		return environement;
	}
	public void setEnvironement(ExecutionEnvironment environement) {
		this.environement = environement;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public int getExecutedCommands() {
		return executedCommands;
	}
	public void setExecutedCommands(int executedCommands) {
		this.executedCommands = executedCommands;
	}
	public void incExec(){
		this.executedCommands++;
	}
	
}
