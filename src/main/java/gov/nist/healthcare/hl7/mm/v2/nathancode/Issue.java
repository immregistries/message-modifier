package gov.nist.healthcare.hl7.mm.v2.nathancode;

public class Issue {
	private IssueType issueType;
	private String description;
	
	public Issue(IssueType issueType, String description) {
		this.issueType=issueType;
		this.description=description;
	}
	
	public String toString() {
		return issueType.toString() + " : " + description ;
	}
	
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
