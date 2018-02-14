package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class CleanFunction extends Function {
	
	public CleanFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{
		
		if(parameterMap.size() != 0) {
			Issue issueError = new Issue(IssueType.Error,"The CleanFunction does not take any arguments" +"\n"+"\n");
	 		context.getIssues().add(issueError);
		}
		
		if(selector != null) {
			Issue issueInformation = new Issue(IssueType.Warning,"The targeted reference has no effect, the clean function acts on the whole message" +"\n"+"\n");
	 		context.getIssues().add(issueInformation);
			
		}
		
		    String resultText = context.getMessage();
		    Boolean noLastSlash = false;
		    if(parameterMap.size() > 0){
		    	noLastSlash = Boolean.valueOf(parameterMap.get("NO LAST SLASH"));
		    }
		    BufferedReader inResult = new BufferedReader(new StringReader(resultText));
		    resultText = "";
		    String lineResult;
		    try {
				while ((lineResult = inResult.readLine()) != null) {
				  lineResult = lineResult.trim();
				  if (lineResult.length() > 0) {
				    String finalLine = "";

				    String headerStart = null;
				    if (lineResult.startsWith("MSH|^~\\&|") || lineResult.startsWith("BHS|^~\\&|") || lineResult.startsWith("FHS|^~\\&|")) {
				      headerStart = lineResult.substring(0, 9);
				      lineResult = lineResult.substring(9);
				    }
				    
				    boolean foundFieldData = false;
				    boolean foundCompData = false;
				    boolean foundRepData = false;

				    for (int i = lineResult.length() - 1; i >= 0; i--) {
				      char c = lineResult.charAt(i);

				      if (!foundFieldData) {
				        if (c != '|' && c != '^' && c != '~') {
				          foundFieldData = true;
				          foundRepData = true;
				          foundCompData = true;
				        }
				      } else if (!foundRepData) {
				        if (c != '^' && c != '~') {
				          foundRepData = true;
				          foundCompData = true;
				        }
				      } else if (!foundCompData) {
				        if (c != '^') {
				          foundCompData = true;
				        }
				      }
				      if (foundFieldData) {
				          if (c == '|') {
				            foundRepData = false;
				            foundCompData = false;
				            finalLine = c + finalLine;
				          } else if (c == '~') {
				            if (foundRepData) {
				              finalLine = c + finalLine;
				            }
				            foundCompData = false;
				          } else if (c == '^') {
				            if (foundCompData) {
				              finalLine = c + finalLine;
				            }
				          } else {
				            finalLine = c + finalLine;
				          }
				        }
				      }
				    if (noLastSlash) {
				        resultText += finalLine +  "\r";
				      } else {
				        resultText += finalLine + "|"  + "\r";
				      }

				      if (headerStart != null) {
				        resultText = headerStart + resultText;
				      }
				    }
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      context.setMessage(resultText);
		}
	


}
