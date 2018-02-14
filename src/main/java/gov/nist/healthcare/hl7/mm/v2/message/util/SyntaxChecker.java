package gov.nist.healthcare.hl7.mm.v2.message.util;

import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;

public class SyntaxChecker {
	
	
	public String CheckSyntax(String script) throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parseLineByLine("PID5=\"\";\rPID-6=\"\";\rPdID-7,\"\";");
		String result="";
		for(Issue i : mmScript.getSyntax()) {
		    result = result + i.toString() +"\n";
		}
		if(mmScript.getSyntax().isEmpty()) {
			return "The syntax is correct";
		} else {
			return result;

		}
		
		

		
	}
	

}
