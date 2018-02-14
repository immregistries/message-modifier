package gov.nist.healthcare.hl7.mm.v2.test.syntax;

import org.junit.Test;

import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.UseCommand;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;

public class UseCommandTest {
	
	@Test
	public void useContext() throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parse("use context::Immunization;");
		assert(mmScript.getCommands().size() == 1);
		assert(mmScript.getCommands().get(0) instanceof UseCommand);
		assert(((UseCommand) mmScript.getCommands().get(0)).getKey().equals("context"));
		assert(((UseCommand) mmScript.getCommands().get(0)).getValue().equals("Immunization"));
	}

}
