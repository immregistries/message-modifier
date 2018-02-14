package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

public class MapFunction extends Function {
	
	public MapFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{
		
		if(parameterMap.size() != 2) {
			Issue issueError = new Issue(IssueType.Error,"Wrong number of arguments input. More details are available on information section" + "\n" + "\n");
			Issue issueInformation = new Issue(IssueType.Information,"The mapfunction takes 2 arguments : " + "\n"+  "-The value to change in the message with the value to replace with"+ "\n" + "-A default value if the first argument is not found in the message" + "\n" + "Exemple : for $RXA-5.2 call map(PCV 13 => 03, Default => Unknown)" + "\n"+ "\n");
	 		context.getIssues().add(issueError);
	 		context.getIssues().add(issueInformation);
	 			
			
		}else {
			
		
		
		Iterator<Entry<String, String>> it = parameterMap.entrySet().iterator();
		
		while (it.hasNext()) {
			
		    Map.Entry<String, String> entry = (Map.Entry)it.next();
		    String key = entry.getKey();
		    
			String resultText = context.getMessage();
			String value;
			try {
				value = this.messageHandler.get(selector, resultText);
		
		    
		    int mapPos = value.toUpperCase().indexOf(key.toUpperCase());
		    if (mapPos == -1 && !(key.toUpperCase().equals("DEFAULT"))) {
		    	String newValue = parameterMap.get("DEFAULT");
			    ConstantValue myValue = new ConstantValue(newValue);	
			    resultText = this.messageHandler.set(selector, resultText,myValue);
			    context.setMessage(resultText);
		    }
		    if (mapPos != -1) {
		    	int startIndex = value.indexOf(key);
		    	int endIndex = startIndex + key.length();
		    	String value1 = value.substring(0, startIndex);
		    	String value2 = value.substring(endIndex, value.length());
		    	String newValue = value1 + parameterMap.get(key) + value2;
		    	ConstantValue myValue = new ConstantValue(newValue);	
		    	resultText = this.messageHandler.set(selector, resultText, myValue);
		    	context.setMessage(resultText);
		    }
			} catch (IOException e) {
				System.out.println("L9iteeeeeeeeeeeek1");
				e.printStackTrace();
			}
		}
		
	   
	

}
	}
}