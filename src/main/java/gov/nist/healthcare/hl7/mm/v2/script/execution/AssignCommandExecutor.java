package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;



import gov.nist.healthcare.hl7.mm.v2.domain.AssignmentCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;

public class AssignCommandExecutor implements CommandExecutor {

	private HL7MessageHandler messageHandler;
	
	public AssignCommandExecutor(HL7MessageHandler messageHandler) {
		super();
		this.messageHandler = messageHandler;
	}

	public boolean handles(CommandType type) {
		return type == CommandType.ASSIGNMENT;
	}

	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException, IOException {
		AssignmentCommand assignmentCommand = (AssignmentCommand) command;
		String message;
		try {
			message = messageHandler.set(assignmentCommand.getReference(), context.getMessage(), assignmentCommand.getValue());
			context.setMessage(message);
		} catch (CommandExecutionException e) {
			System.out.println("L9iteeeeeeeeeeeek2");

	 		throw e;	
		}
		//message = modified
		//context.getMessage = original
		
	}

}
