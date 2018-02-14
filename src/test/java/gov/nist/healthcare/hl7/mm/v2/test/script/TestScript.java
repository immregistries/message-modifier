package gov.nist.healthcare.hl7.mm.v2.test.script;

import java.io.IOException;
import java.util.List;



import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Component;
import gov.nist.healthcare.hl7.mm.v2.domain.Field;
import gov.nist.healthcare.hl7.mm.v2.domain.HL7Path;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.Segment;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.script.execution.MessageModifierService;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ModificationResult;

public class TestScript {
	
	public static void main(String[] args) {
		MessageModifierService mms = new MessageModifierService();
		String script = "for $RXA-5.2 call map(\"PCV 13\" => \"03\", \"Default\" => \"Unknown\");";
		//		String script = "PID-5.1=Watson;";
		ModificationResult modificationResult = new ModificationResult();
//		String msg = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
		modificationResult = mms.modify(msg,script);
		System.out.println(modificationResult.getModificationDetailsList(true));
		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//		String string1 = "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		String string2 = "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		assert(string1.equals(string2));
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
//			
			
			
			
//		MessageModifierService mms = new MessageModifierService();
//		String script = "for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
//		ModificationResult modificationResult = new ModificationResult();
//		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		modificationResult = mms.modify(msg,script);
//		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
//			
//		}
		
//		MessageModifierService mms = new MessageModifierService();
//		String script = "for $RXA-9.2 call trunc(\"max\" => \"5\");";
//		ModificationResult modificationResult = new ModificationResult();
//		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		modificationResult = mms.modify(msg,script);
//		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
				
				
				
		
			
			
//		MMScript mmScript;
//		try {
//			mmScript = Parser.parse("for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");");
//	
//
//		
//
//		CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
//		System.out.println(command.getArgs().get(0).getName());
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TokenMgrError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//			
//		}

		
	


		
//		ReferenceParsed t = new ReferenceParsed();
//		t.setSegmentName("PID");
//		t.setFieldPos(5);
//		ModificationDetails modifyRequest = new ModificationDetails();
//		try {
//			String messageFinal = SetCommand.setValueInHL7("AAAAAAAAAAAAAAAAAAAAA",msg, t, modifyRequest);
//			System.out.println(messageFinal);
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (CommandExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

	}
	

	}

	

