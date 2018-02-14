package gov.nist.healthcare.hl7.mm.v2.test.syntax;

import org.junit.Test;

import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Comparator;
import gov.nist.healthcare.hl7.mm.v2.domain.ConditionalCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;

public class ConditionalCommandTest {
	
	@Test
	public void generalConditionalCommand() throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parse("for $PID-5.2 if (RXA-3.1 == \"Zoubida\") then call trunc(\"max\" => \"2\");");
		assert(mmScript.getCommands().size() == 1);
		assert(mmScript.getCommands().get(0) instanceof ConditionalCommand);
		
		Reference ref = ((ConditionalCommand) mmScript.getCommands().get(0)).getSelector();
		assert(ref.getPath().getSegment().getId().equals("PID"));

		ConditionalCommand command = ((ConditionalCommand) mmScript.getCommands().get(0));		
		assert(command.getCall() instanceof CallCommand);
		assert(command.getCmp().equals(Comparator.EQ));
		assert(command.getReference().getPath().getSegment().getId().equals("RXA"));
		assert(command.getReference().getPath().getField().getId() == 3);
		assert(command.getReference().getPath().getComponent().getId() == 1);
		assert(command.getSelector().getPath().getSegment().getId().equals("PID"));
		assert(command.getValue().equals("Zoubida"));
		assert(command.getCall().getName().equals("trunc"));
		assert(command.getCall().getArgs().get(0).getName().equals("MAX"));
		assert(command.getCall().getArgs().get(0).getValue().getConstant().equals("2"));

		









		

}

}