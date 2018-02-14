package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;



import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;
import gov.nist.healthcare.hl7.mm.v2.domain.Comparator;
import gov.nist.healthcare.hl7.mm.v2.domain.ConditionalCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.functions.Function;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

public class ConditionalCommandExcecutor implements CommandExecutor {

	private HL7MessageHandler messageHandler;
	CallCommandExecutor callCommandExecutor = new CallCommandExecutor(messageHandler);
	
	
	public ConditionalCommandExcecutor(HL7MessageHandler messageHandler) {
		super();
		this.messageHandler = messageHandler;
	}

	public boolean handles(CommandType type) {
		return type == CommandType.IF;
	}

	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException {
		ConditionalCommand conditionalCommand = (ConditionalCommand) command;
		CallCommand callCommand = conditionalCommand.getCall();
		Comparator comp = conditionalCommand.getCmp();
		Reference ref = conditionalCommand.getReference();
		String refValue;
		try {
			refValue = messageHandler.get(ref, context.getMessage());
			String value = conditionalCommand.getValue();
			
			boolean compBool = comp.equals(Comparator.EQ) && refValue.equals(value) ||
						comp.equals(Comparator.NE) && !refValue.equals(value) ;
			
			if(compBool) {
				callCommand.setSelector(conditionalCommand.getSelector());
				callCommandExecutor.execute(context, callCommand);
			}

		} catch (IOException e) {
				Issue issue = new Issue(IssueType.Warning,"Couldn't get value from the message in ConditionalCommandExecutor  :" + e.getMessage());
		 		throw new CommandExecutionException(issue);	
		}

					
			
		}
		
		

	
}
