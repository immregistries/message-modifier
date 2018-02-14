package gov.nist.healthcare.hl7.mm.v2.test.syntax;

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
import gov.nist.healthcare.hl7.mm.v2.message.util.SyntaxChecker;
import gov.nist.healthcare.hl7.mm.v2.script.execution.MessageModifierService;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ModificationResult;

public class ErrorTest {
	
	
	@Test
	public void parserLineByLineTest() throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parseLineByLine("PID-5=\"\";\rPID-6=\"\";\rPID-7=\"\";");		
		assert(mmScript.getCommands().size() == 3);
		assert(mmScript.getCommands().get(0) instanceof AssignmentCommand);
		assert(mmScript.getCommands().get(1) instanceof AssignmentCommand);
		assert(mmScript.getCommands().get(2) instanceof AssignmentCommand);

		
		
		Reference ref = ((AssignmentCommand) mmScript.getCommands().get(0)).getReference();
		assert(ref.getPath().getSegment().getId().equals("PID"));
		assert(ref.getPath().getField().getId() == 5);
		assert(ref.getPath().toString().equals("PID[1]-5[1]"));
		
		Value value = ((AssignmentCommand) mmScript.getCommands().get(0)).getValue();
		assert(value instanceof ConstantValue);
		assert(((ConstantValue) value).getConstant().equals(""));
		
		Value value2 = ((AssignmentCommand) mmScript.getCommands().get(1)).getValue();
		assert(value2 instanceof ConstantValue);
		assert(((ConstantValue) value).getConstant().equals(""));
		
		Value value3 = ((AssignmentCommand) mmScript.getCommands().get(2)).getValue();
		assert(value3 instanceof ConstantValue);
		assert(((ConstantValue) value).getConstant().equals(""));
	}
	
	@Test
	public void exeptionTestInParsing() throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parseLineByLine("PID5=\"\";\rPID-6=\"\";\rPdID-7,\"\";");
		
//		System.out.println(mmScript.getCommands().size());
//		for(Issue i : mmScript.getSyntax()) {
//			System.out.println(i.toString());
//		}
		
		assert(mmScript.getCommands().size() == 1);
		assert(mmScript.getSyntax().size() == 2);



	}
	
	@Test
	public void exeptionTestWithModify() throws ParseException, TokenMgrError {	
		String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
			    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
			    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
			    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
			    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
			    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
		
//		String modificationScript = "NK1-2.1=\"BobMarley\";\rPIiX-5=\"\";\rORC-6.99=\"\";\rPdID-7,\"\";";
		String modificationScript = "NK1-2.1=\"BobMarley\";\rNK1-3.2=\"\";\rORC-6.99=\"\";\rRXA-7=\"\";";

		MessageModifierService mms = new MessageModifierService();
		ModificationResult modificationResult = new ModificationResult();
		modificationResult = mms.modify(messageOriginal,modificationScript);
		
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
//
//		}
		
		System.out.println(modificationResult.getModificationDetailsList(true));

		
		System.out.println(modificationResult.getResultMessage());

	}

}
