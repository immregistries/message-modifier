package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;

import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class InsertAfterFunction extends Function {
	
	public InsertAfterFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{

		
		String resultText = context.getMessage();
		
		ReferenceParsed targetReference = selector.toRefParsed();

		int repeat = targetReference.getSegmentRepeat();
		int compteur = 1;
		
		String segID = parameterMap.get("SEGMENT ID");
		String segIDToCopyFrom = parameterMap.get("COPY VALUES FROM");

        BufferedReader inResult = new BufferedReader(new StringReader(resultText));
        String line;
		try {
			line = inResult.readLine();

        
        if(parameterMap.containsKey("COPY VALUES FROM")){
        	 
	        String lineToCopy = "";
	        
             while(line != null){
	        	if(line.startsWith(segIDToCopyFrom)){
	        		lineToCopy = line.substring(3, line.length());
	        	}
	        	line = inResult.readLine();
	        }
             inResult = new BufferedReader(new StringReader(resultText));
             resultText = "";
             line = inResult.readLine();
             
             while(line != null){
            	resultText += line + "\n";
             	if(line.startsWith(targetReference.getSegmentName())){
             		if(compteur == repeat){
             			resultText += segID + lineToCopy + "\n";
             		}
             		compteur++;
             	}
             	line = inResult.readLine();
             }
		}else{
			resultText = "";
	        while(line != null){
	        	resultText += line+"\n";
	        	if(line.startsWith(targetReference.getSegmentName())){
	        		if(compteur == repeat){
	        			resultText += segID + "|"+"\n";
	        		}
	        		compteur++;
	        	}
	        	line = inResult.readLine();
	        }
		}
        resultText.substring(0, resultText.length()-1);
		context.setMessage(resultText);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}