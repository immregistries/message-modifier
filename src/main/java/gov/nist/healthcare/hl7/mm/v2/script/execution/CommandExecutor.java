package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;

public interface CommandExecutor {
	
	public boolean handles(CommandType type);
	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException, IOException;
	
}
