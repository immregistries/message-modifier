package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.util.List;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

public class ModificationResult {
	List<Issue> issues;
	boolean status;
	int numberOfExecutedCommands;
	String resultMessage;
	boolean debug=true;
	
	
	public ModificationResult(List<Issue> issues, boolean status, int numberOfExecutedCommands, String resultMessage) {
		super();
		this.issues = issues;
		this.status = status;
		this.numberOfExecutedCommands = numberOfExecutedCommands;
		this.resultMessage = resultMessage;
	}
	
	public String getModificationDetailsList(boolean debug) {
		String detailsDebugOff = "";
		String detailsDebugOn = "";
		String details = "";
		if (debug==true) {
			for(Issue e:issues) {
				  details = e.toString();
				  detailsDebugOn += details;
			}
			return detailsDebugOn;
		} else {
			for(Issue i : issues) {
				if(i.getIssueType().equals(IssueType.Error) || i.getIssueType().equals(IssueType.Warning)) {
					details = i.toString();
					detailsDebugOff += details;

				}
			}
			return detailsDebugOff;

		}

	}

	public void addIssue(Issue issue) {
		
		issues.add(issue);
		
	}
	

	
	public boolean hasError() {
		for(Issue i : issues) {
			if(i.getIssueType().equals(IssueType.Error)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasWarning() {
		for(Issue i : issues) {
			if(i.getIssueType().equals(IssueType.Warning)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	
	public ModificationResult() {
		// TODO Auto-generated constructor stub
	}

	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getNumberOfExecutedCommands() {
		return numberOfExecutedCommands;
	}
	public void setNumberOfExecutedCommands(int numberOfExecutedCommands) {
		this.numberOfExecutedCommands = numberOfExecutedCommands;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
