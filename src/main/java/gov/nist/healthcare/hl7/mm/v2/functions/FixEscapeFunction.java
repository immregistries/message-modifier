package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;

import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class FixEscapeFunction extends Function {
	
	public FixEscapeFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{
		if(parameterMap.size() != 0) {
			Issue issueError = new Issue(IssueType.Error,"The FixEscapeFunction does not take any arguments" +"\n"+"\n");
	 		context.getIssues().add(issueError);
		}
		
		if(selector != null) {
			Issue issueInformation = new Issue(IssueType.Warning,"The targeted reference has no effect, the FixEscapeFunction acts on the whole message" +"\n"+"\n");
	 		context.getIssues().add(issueInformation);
			
		}
		String resultText = context.getMessage();
		if (resultText.length() > 9) {
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < resultText.length(); i++) {
	          char c = resultText.charAt(i);
	          if (i < 8 || c != '\\') {
	            sb.append(c);
	          } else {
	            if ((i + 2) >= resultText.length() || resultText.charAt(i + 2) != '\\') {
	              sb.append("\\E\\");
	            } else {
	              sb.append(c);
	            }
	          }
	        }
	        resultText = sb.toString();
	    context.setMessage(resultText);
	}
	
		
}
}