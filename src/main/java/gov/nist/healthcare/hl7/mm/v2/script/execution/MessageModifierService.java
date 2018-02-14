package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.message.util.SimpleHL7MessageHandler;

public class MessageModifierService {
	
	List<CommandExecutor> commandExecutors = new ArrayList<CommandExecutor>();
	private HL7MessageHandler messageHandler;
	
	public MessageModifierService(){
		this.messageHandler = new SimpleHL7MessageHandler();
		this.commandExecutors.add(new UseCommandExecutor());
		this.commandExecutors.add(new CallCommandExecutor(messageHandler));
		this.commandExecutors.add(new AssignCommandExecutor(this.messageHandler));
		this.commandExecutors.add(new ConditionalCommandExcecutor(this.messageHandler));
	}
	
	public ModificationResult modify(String msg, String scr){
		ExecutionContext context = new ExecutionContext();
		MMScript script = this.parseScript(context, scr);
		context.setMessageOriginal(msg);
		context.setMessage(msg);
		if(script != null){
			context.setScript(script);
			Issue issue = new Issue(IssueType.Information,"The executed script is : " +"\n" + scr + "\n"+"\n");
			context.issues.add(issue);
			for(Command c : script.getCommands()){
				try {
					this.execute(context, c);
					Issue issueInformation = new Issue(IssueType.Information,"The resulted message of the execution of the command " + c.toString() + " is : " +"\n"+ context.getMessage()+"\n"+"\n");
					context.issues.add(issueInformation);
				} 
				catch (CommandExecutionException e) {
					context.issues.add(e.getIssue());
				}
			}
			Issue issueExecutedCommands = new Issue(IssueType.Information,"The number of executed commands is : " + context.getExecutedCommands() +"\n"+"\n");
			context.issues.add(issueExecutedCommands);
		}

		return new ModificationResult(context.getIssues(), script != null, context.getExecutedCommands(), context.getMessage());		
	}
	
	private MMScript parseScript(ExecutionContext context, String scr){
		try {
			return Parser.parse(scr);
		} catch (ParseException e) {
			Issue issue = new Issue(IssueType.Error,e.getMessage());
			context.issues.add(issue);
			e.printStackTrace();
			return null;
		} catch (TokenMgrError e) {		
			Issue issue = new Issue(IssueType.Error,e.getMessage());
			context.issues.add(issue);
			e.printStackTrace();

			return null;
		}
	}
	
	private void execute(ExecutionContext context, Command c) throws CommandExecutionException{
		for(CommandExecutor exec : commandExecutors){
			if(exec.handles(c.getType())){
				try {
					exec.execute(context, c);
				} catch (IOException e) {
					Issue issue = new Issue(IssueType.Warning,"Couldn't excecute commands in ModifierService :" + e.getMessage());
			 		throw new CommandExecutionException(issue);	
			 		
				}
				context.incExec();
			}
		}
	}
}
