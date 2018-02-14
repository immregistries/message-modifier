package gov.nist.healthcare.hl7.mm.v2.test.script;

import java.util.List;

import org.junit.Test;

import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.script.execution.MessageModifierService;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ModificationResult;

public class FunctionTestScript {
	
	private void runTest(String messageOriginal, String modificationScript, String messageFinal) {
		MessageModifierService mms = new MessageModifierService();
		ModificationResult modificationResult = new ModificationResult();
		modificationResult = mms.modify(messageOriginal,modificationScript);
		
//		System.out.println("The following Test : " + modificationScript +"\n" + "Resulted in this message : \n" + modificationResult.getResultMessage() +"\n" +"The message original is :"+"\n" +messageOriginal +"\n" +"The expected message is :" + "\n" + messageFinal +"\n");
//		System.out.println("The number of executed commands is : " + modificationResult.getNumberOfExecutedCommands() +"\n"+"\n");

		System.out.println(modificationResult.getModificationDetailsList(true));

//		System.out.println(modificationResult.getResultMessage()+"0");
//		System.out.println(messageFinal+"0");
		assert(modificationResult.getResultMessage().replace("\n", "").replace("\r", "").equals(messageFinal.replace("\n", "").replace("\r", "")));
	}
	
	
	@Test
	public void assignmentCommandTestScript() throws ParseException, TokenMgrError {
		
		 {
		        String modificationScript = "for $RXA-5.2 call map(\"PCV 13\" => \"03\", \"Default\" => \"Unknown\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^03^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-4 call map(\"MMR\" => \"03\", \"Default\" => \"Unknown\");";
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104|Unknown|133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^ABC 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    // Truncate
		    {
		        String modificationScript = "for $RXA-9.2 call trunc(\"max\" => \"5\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Admin^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-7.2 call trunc(\"Max\" => \"5\", \"cut\"=>\"left\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milli^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"1\", \"cut\"=>\"right\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^3^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"2\", \"cut\" => \"right\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-5.3 call trunc(\"MAX\" => \"2\", \"cut\" => \"left\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CV|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA-5.3 call trunc(\"MAX\" => \"1\", \"cut\" => \"left\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^C|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    // Truncate and mapping
		    
		    {
		        String modificationScript = "for $RXA-5.1 call trunc(\"MAX\" => \"1\", \"cut\"=>\"right\");\rfor $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
		        
		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        String messageFinal =    "RXA|0|1|20170104||3^ABC 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    // Clearing fields
		    
		    {
		        String modificationScript = "for $PID call clear();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    // Testing the segment repeat with clear
		    {
		        String modificationScript = "for $PID[2] call clear();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\r" + 
		        							"PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\r" + 
										"PID|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		   // PID-5= "Something" is not for $PID-5 call clear (PID-5.1 =? PID-5  ???)
		    
//		    {
//		        String modificationScript = "for $PID-5 call clear();";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR|||Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
		    {
		        String modificationScript = "for $PID-5.1 call clear();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    // Same problem here
//		    {
//		        String modificationScript = "for $PID-3 call clear();";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
		    
		    // When calling PID-5.2 it clears PID-5.1. Problem in the code ? 
//		    {
//		        String modificationScript = "for $PID-5.2 call clear();";
//		        
//		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
		    
		    //Cleaning fields
		    {
		        String modificationScript = "call clean();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\r" +
						     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\r" +
		    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "call clean(\"no last slash\" => \"true\");";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\r" +
						     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208\r" +
		    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "call clean(\"no last slash\" => \"false\");";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\r" +
						     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\r" +
		    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $PID call clean();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\r" +
						     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\r" +
		    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    // Questions about fixes : call fixAmpersand();  call fixEscape(); what does they do?
		    // For fixAmpersand the any & becomes \T\ (except for in heading)
		    // For fixEscape() any single \ becomes \E\ (except for in heading)
		    {
		        String modificationScript = "call fixAmpersand();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\T\\Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "call fixEscape();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\E\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $PID call fixAmpersand();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\T\\Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $PID call fixEscape();";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\E\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    
		    
		    // Adding and removing segments
		    
		    {
		        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\");";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
		        						 "PD1|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $PID call insertBefore(\"Segment Id\" => \"ABC\");";
		        
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "ABC|\n" +
		        						 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $RXA[2] call insertAfter(\"Segment Id\" => \"RXR\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "RXR|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA call insertAfter(\"Segment Id\" => \"RXR\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "RXR|\n"+
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
									    
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }  
		    {
		        String modificationScript = "call insertLast(\"Segment Id\" => \"PV1\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "PV1|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "call insertFirst(\"Segment Id\" => \"PV1\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PV1|\n" +
		        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }  
		    {
		        String modificationScript = "call insertFirst(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
		        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "call insertLast(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" +
									    "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $PD1 call insertBefore(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
		        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $RXA[2] call insertAfter(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";        
		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
										    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
									    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" +
									    "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    {
		        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\", \"if missing\" => \"true\");";
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
		        						 "PD1|";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    
		    // If then else
		    
		    {
		        String modificationScript = "for $PID if(PID-7 == \"20160626\") then call clear();";
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" ;
		        String messageFinal =    "PID|" ;
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $PID-5.2 if (PID-5.2 == \"Jeramiah\") then call trunc(\"max\" => \"2\");";
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
						 				 "PD1||||||||||||||||||";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Je^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
		        						 "PD1||||||||||||||||||";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $PID-5.2 if (PID-5.2 == \"Zoubida\") then call trunc(\"max\" => \"2\");";
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
						 				 "PD1||||||||||||||||||";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
		        						 "PD1||||||||||||||||||";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    {
		        String modificationScript = "for $PID-5.2 call trunc(\"max\" => \"2\");"; 
		        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n";
		        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Je^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n";
		        runTest(messageOriginal, modificationScript, messageFinal);
		    }
		    
		    // Trying mapFunction errors display
		    
//		    {
//		        String modificationScript = "for $RXA-5.2 call map(\"PCV 13\" => \"03\");";
//		        
//		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		        String messageFinal =    "RXA|0|1|20170104||133^03^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
//		    
//		    {
//		        String modificationScript = "for $RXA-7.2 call trunc();";
//		        
//		        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milli^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }
//		    {
//		        String modificationScript = "call insertFirst(\"Segment Id\" => \"BHS\", \"copyrefewfewfwdf values from\" => \"MSeH\");";
//		        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
//										    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
//										    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
//										    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
//										    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
//										    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
//		        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
//		        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
//									    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
//									    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
//									    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
//									    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
//									    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
//		        runTest(messageOriginal, modificationScript, messageFinal);
//		    }

	}
	
}
