package gov.nist.healthcare.hl7.mm.v2.message.util;

import java.io.IOException;


import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.Value;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;

public interface HL7MessageHandler {
	
	public String get(Reference ref, String message) throws IOException, CommandExecutionException;
	public String set(Reference ref, String message, Value value) throws CommandExecutionException, IOException;

}
