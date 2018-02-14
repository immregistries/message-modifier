package gov.nist.healthcare.hl7.mm.v2.test.script;

import java.util.List;

import org.junit.Test;

import gov.nist.healthcare.hl7.mm.v2.domain.AssignmentCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.Value;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.script.execution.MessageModifierService;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ModificationResult;

public class AssignScriptTest {
	
	private void runTest(String messageOriginal, String modificationScript, String messageFinal) {
		MessageModifierService mms = new MessageModifierService();
		ModificationResult modificationResult = new ModificationResult();
		modificationResult = mms.modify(messageOriginal,modificationScript);
		
		System.out.println("The following Test : " + modificationScript +"\n" + "Resulted in this message : \n" + modificationResult.getResultMessage() +"\n" +"The message final is :"+"\n" +messageFinal +"\n");
		System.out.println("The number of executed commands is : " + modificationResult.getNumberOfExecutedCommands() +"\n");
//
		List<Issue> issues = modificationResult.getIssues();
		for(Issue e:issues) {
			System.out.println(e.toString());
	}
		
		
//		System.out.println(modificationResult.getResultMessage()+"0");
//		System.out.println(messageFinal+"0");

		assert(modificationResult.getResultMessage().equals(messageFinal+ "\r"));
	}
	
	
	@Test
	public void assignmentCommandTestScript() throws ParseException, TokenMgrError {
		
//		 {
//		        String modificationScript = "PID5=\"\";\rPID-F=\"\";\rPdID-7,\"\";";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		      }
		
		{	
		String modificationScript = "PID-5=\"\";";
	      
	      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	      String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	
	      runTest(messageOriginal, modificationScript, messageFinal);


	}
		 {
		        String modificationScript = "PID-5.1=\"\";";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 
		 {
		        String modificationScript = "PID-5.1=\"Watson\";";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 {
		        String modificationScript = "PID-5.2=\"Watson\";";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Watson^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 
		 {
		        String modificationScript = "PID-5.2 = $PID-5.1;";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 
		 {
		        String modificationScript = "PID-5[2].1=\"Hello\";";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hello^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 
		 {
		        String modificationScript = "PID-3.1 = $PID-5.1;";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hossam^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Holmes^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hossam^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		 
//		 {
//		        String modificationScript = "PID-5[*].1 = \"INSTANCEFIELD\";";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hossam^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||INSTANCEFIELD^Holmes^Z^IV^^^L~INSTANCEFIELD^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
		 
		
//		 {
//		        String modificationScript = "PID-3.1 = $PID-5[2].1;";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hossam^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||Hossam^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L~Hossam^Jamila^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
//		 
	


	

		
		{
	        String modificationScript = "PID-5=\"\";\rPID-6=\"\";";
	        
	        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        runTest(messageOriginal, modificationScript, messageFinal);
	      }
	    {
	        String modificationScript = "PID-5=\"\";\rPID-6=\"\";\rPID-7=\"\";";
	        
	        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        runTest(messageOriginal, modificationScript, messageFinal);
	      }
	    {
	        String modificationScript = "PID-5=\"\";\rPID-6=\"\";\rPID-7=\"\";";
	        
	        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
	        runTest(messageOriginal, modificationScript, messageFinal);
	      }
		
	}
	
}
