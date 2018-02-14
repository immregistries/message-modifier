package gov.nist.healthcare.hl7.mm.v2.nathancode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModificationDetails {
	private String messageOriginal = "";
	private String messageFinal = "";
	private String modificationScript = "";
	private Map<String, String> messageMap = new HashMap<>();
	private String segmentSeparator = "\r";
	private Exception exception = null;
	
	private String commandLine;
	private List<Issue> issues = new ArrayList<>();
	private Boolean debug;
	

	public String getModificationDetailsList() {
		String detailsDebugOff = "";
		String details = "";
		if (debug==true) {
			return issues.toString();
		} else {
			for(Issue i : issues) {
				if(i.getIssueType().equals(IssueType.Error) || i.getIssueType().equals(IssueType.Warning)) {
					details = i.toString();
				}
				detailsDebugOff += details;
			}
			return detailsDebugOff;
		}

	}

	public void addIssue(Issue issue) {
		
		issues.add(issue);
		
	}
	
	public List<Issue> getIssues() {
		return issues;
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

	public String getSegmentSeparator() {
		return segmentSeparator;
	}

	public void setSegmentSeparator(String segmentSeparator) {
		this.segmentSeparator = segmentSeparator;
	}

	public String getModificationScript() {
		return modificationScript;
	}

	public void setModificationScript(String modificationScript) {
		this.modificationScript = modificationScript;
	}

	public Exception getException() {
		return exception;
	}

	protected void setException(Exception exception) {
		this.exception = exception;
	}


	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}



	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public void setMessageMap(Map<String, String> messageMap) {
		this.messageMap = messageMap;
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

	public void addToMessageMap(String messageKey, String message) {
		messageMap.put(messageKey, message);
	}

	public Map<String, String> getMessageMap() {
		return messageMap;
	}
	
	
}
