package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;

import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class TruncFunction extends Function {

	public TruncFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	public static final String PARAM_MAX = "MAX";
	public static final String PARAM_CUT = "CUT";

	public static final String SIDE_RIGHT = "RIGHT";
	public static final String SIDE_LEFT = "LEFT";

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{
		
		if( parameterMap.size() == 0 || parameterMap.size() > 2) {
			
			Issue issueError = new Issue(IssueType.Error,"Wrong number of arguments input. More details are available on information section" + "\n" + "\n");
			Issue issueInformation = new Issue(IssueType.Information,"The truncFunction takes 2 arguments : " + "\n"+  "- Mandatory : The number of character to truncate to"+ "\n" + "- Optional : The side to truncate from" + "\n" + "Exemple 1 : for $PID-5.2 call trunc(max => 2)" + "\n"+ "Exemple 2 : for $RXA-7.2 call trunc(Max => 5, cut => left)" + "\n"+ "\n");
	 		context.getIssues().add(issueError);
	 		context.getIssues().add(issueInformation);
			
		}else {
			
			Issue issueInformation = new Issue(IssueType.Warning,"No side was choosen, will automaticaly cut from the right " + "\n"+ "\n");
	 		context.getIssues().add(issueInformation);
		
		String maxString = parameterMap.get(PARAM_MAX);

		if (maxString != null) {
			int max = -1;
			try {
				max = Integer.parseInt(maxString);
			} catch (NumberFormatException nfe) {
				// ignore
			}
			if (max > -1) {
				
				String resultText = context.getMessage();
				
				String value;
				try {
					value = this.messageHandler.get( selector, resultText);
				
				if (value.length() > max) {
					String side;
					try{
						side = parameterMap.get(PARAM_CUT).toUpperCase();
					}
					catch(Exception e){
						side = SIDE_LEFT;
					}
					if (side.equals(SIDE_RIGHT)) {
						// trunc(4, right) Hello --> ello 
						value = value.substring(value.length() - max);
					} else {
						// trunc(4, left) Hello --> Hell
						value = value.substring(0, max);
					}
					ConstantValue myValue = new ConstantValue(value);
					resultText =this.messageHandler.set(selector, resultText,myValue);
					context.setMessage(resultText);
				}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	
		}
}
}