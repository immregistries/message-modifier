package gov.nist.healthcare.hl7.mm.v2.test.syntax;

import org.junit.Test;

import gov.nist.healthcare.hl7.mm.v2.domain.AssignmentCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.Value;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;

public class CallCommandTest {
	

	@Test
	public void generalCallCommand() throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parse("for $PID call clear();");
		assert(mmScript.getCommands().size() == 1);
		assert(mmScript.getCommands().get(0) instanceof CallCommand);
		
		Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
		assert(ref.getPath().getSegment().getId().equals("PID"));

		CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
		assert(command.getName().equals("clear"));

	}
		@Test
		public void GeneralCallClean() throws ParseException, TokenMgrError {
			MMScript mmScript = Parser.parse("for $PID-5.2 call clean();");
			assert(mmScript.getCommands().size() == 1);
			assert(mmScript.getCommands().get(0) instanceof CallCommand);
			
			Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
			assert(ref.getPath().getSegment().getId().equals("PID"));
			assert(ref.getPath().getField().getId() == 5);
			assert(ref.getPath().getComponent().getId() == 2);
			CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
			assert(command.getName().equals("clean"));
		}
		
		@Test
		public void GeneralCallClear() throws ParseException, TokenMgrError {
			MMScript mmScript = Parser.parse("for $PID-5.2 call clear();");
			assert(mmScript.getCommands().size() == 1);
			assert(mmScript.getCommands().get(0) instanceof CallCommand);
			
			Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
			assert(ref.getPath().getSegment().getId().equals("PID"));
			assert(ref.getPath().getField().getId() == 5);
			assert(ref.getPath().getComponent().getId() == 2);
			CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
			assert(command.getName().equals("clear"));
		}
		@Test
		public void CallCommandMap() throws ParseException, TokenMgrError {
			MMScript mmScript = Parser.parse("for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");");
			assert(mmScript.getCommands().size() == 1);
			assert(mmScript.getCommands().get(0) instanceof CallCommand);
			
			Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
			assert(ref.getPath().getSegment().getId().equals("RXA"));
			assert(ref.getPath().getField().getId() == 5);
			assert(ref.getPath().getComponent().getId() == 2);
			CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
			assert(command.getName().equals("map"));
			assert(command.getArgs().get(0).getName().equalsIgnoreCase("PCV"));
			assert(command.getArgs().get(0).getValue().getConstant().equalsIgnoreCase("ABC"));
			assert(command.getArgs().get(1).getName().equalsIgnoreCase("Default"));
			assert(command.getArgs().get(1).getValue().getConstant().equalsIgnoreCase("Unknown"));
					
		
}

		@Test
		public void CallCommandInsertSegment() throws ParseException, TokenMgrError {
			MMScript mmScript = Parser.parse("for $PID call insertAfter(\"Segment Id\" => \"PD1\");");
			assert(mmScript.getCommands().size() == 1);
			assert(mmScript.getCommands().get(0) instanceof CallCommand);
			
			Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
			assert(ref.getPath().getSegment().getId().equals("PID"));
			CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
			assert(command.getName().equals("insertAfter"));
			assert(command.getArgs().get(0).getName().equalsIgnoreCase("Segment Id"));
			assert(command.getArgs().get(0).getValue().getConstant().equalsIgnoreCase("PD1"));					
		
}
		@Test
		public void CallCommandInsertLast() throws ParseException, TokenMgrError {
			MMScript mmScript = Parser.parse("call insertLast(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");");
			assert(mmScript.getCommands().size() == 1);
			assert(mmScript.getCommands().get(0) instanceof CallCommand);
			
			Reference ref = ((CallCommand) mmScript.getCommands().get(0)).getSelector();
			CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
			assert(command.getName().equals("insertLast"));
			assert(command.getArgs().get(0).getName().equalsIgnoreCase("Segment Id"));
			assert(command.getArgs().get(0).getValue().getConstant().equalsIgnoreCase("BHS"));	
			assert(command.getArgs().get(1).getName().equalsIgnoreCase("copy values from"));
			assert(command.getArgs().get(1).getValue().getConstant().equalsIgnoreCase("MSH"));
		
		}
		}