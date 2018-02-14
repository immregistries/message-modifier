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

public class InsertFirstFunction extends Function {
	
	public InsertFirstFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{
		
	
		String resultText = context.getMessage();


			
			String segIDToInsert = parameterMap.get("SEGMENT ID");
			String segIDToCopyFrom = parameterMap.get("COPY VALUES FROM");
			
			 if(parameterMap.containsKey("COPY VALUES FROM")){
				
				BufferedReader inResult = new BufferedReader(new StringReader(resultText));
		        String line;
				try {
					line = inResult.readLine();

		        String lineToCopy = "";
		        
		        while(line != null){
		        	if(line.startsWith(segIDToCopyFrom)){
		        		lineToCopy = line.substring(3, line.length()) + "\n" ;
		        	}
		        	line = inResult.readLine();
		        }
		        resultText = segIDToInsert + lineToCopy + resultText;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else{
	        resultText = segIDToInsert + "|" + "\n" + resultText ; 
		} 
			context.setMessage(resultText);

		}

}
