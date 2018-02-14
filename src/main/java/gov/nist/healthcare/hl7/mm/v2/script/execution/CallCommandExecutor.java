package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;
import gov.nist.healthcare.hl7.mm.v2.functions.CleanFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.ClearFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.FixAmpersandFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.FixEscapeFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.Function;
import gov.nist.healthcare.hl7.mm.v2.functions.InsertAfterFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.InsertBeforeFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.InsertFirstFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.InsertLastFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.MapFunction;
import gov.nist.healthcare.hl7.mm.v2.functions.TruncFunction;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;

public class CallCommandExecutor implements CommandExecutor {

	
	private static List<Function> functions = new ArrayList<Function>();

	public CallCommandExecutor(HL7MessageHandler messageHandler) {
		this.functions.add(new TruncFunction(messageHandler, "trunc"));
		this.functions.add(new MapFunction(messageHandler, "map"));
		this.functions.add(new CleanFunction(messageHandler, "clean"));
		this.functions.add(new ClearFunction(messageHandler, "clear"));
		this.functions.add(new FixAmpersandFunction(messageHandler, "fixAmpersand"));
		this.functions.add(new FixEscapeFunction(messageHandler, "fixEscape"));
		this.functions.add(new InsertAfterFunction(messageHandler, "insertAfter"));
		this.functions.add(new InsertBeforeFunction(messageHandler, "insertBefore"));
		this.functions.add(new InsertFirstFunction(messageHandler, "insertFirst"));
		this.functions.add(new InsertLastFunction(messageHandler, "insertLast"));

	}

	public boolean handles(CommandType type) {
		return type == CommandType.CALL;
	}

	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException {
		CallCommand callCommand = (CallCommand) command;
		Function f = this.functions.stream().filter(x -> x.getName().equals(callCommand.getName())).findFirst().orElse(null);
		if(f != null) {
			
			try {
				f.execute(context, callCommand.getSelector(), callCommand.toMapArg());
			} catch (CommandExecutionException e) {
				Issue issue = new Issue(IssueType.Warning,"Couldn't excecute " + f.getName()  +" function : " + callCommand.getSelector().getPath().toString()+ " could not be found in the message" + "\n" );
		 		throw new CommandExecutionException(issue);	
			}
		}
		
	}

}
