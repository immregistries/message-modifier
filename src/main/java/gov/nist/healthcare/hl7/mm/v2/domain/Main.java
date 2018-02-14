package gov.nist.healthcare.hl7.mm.v2.domain;

import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;

public class Main {

	public static void main(String[] args) throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parseLineByLine("use context::Immunization; usfghe context::Immunization; use context::Immunization;");
		System.out.println(mmScript.getCommands().size());
		System.out.println(mmScript.getSyntax());
	}
}
