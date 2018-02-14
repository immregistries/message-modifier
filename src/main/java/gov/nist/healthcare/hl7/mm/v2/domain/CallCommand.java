package gov.nist.healthcare.hl7.mm.v2.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;

public class CallCommand extends Command {

	private String name;
	private List<Arg> args;
	private Reference targetReference = null;
	
	
	public String toString() {
		if(!args.isEmpty()) {
		return name + "(" + toStringArgs() + ")";
	}else {
		return name;
	}
	}
	public String toStringArgs() {
		String details="";
		for(Arg e:args) {
			String detail = e.toString()+",";
		    details += detail;
		}
		return details.substring(0, details.length()-1);
	}
	
	public Map<String, String> toMapArg() throws CommandExecutionException {		
		Map<String, String> parameterMap = new LinkedHashMap<>();
		
		for(Arg arg:args) {
			parameterMap.put(arg.getName(), arg.getValue().getConstant());
		}
		
		
		return parameterMap;
	}
	
	
	public CallCommand(Reference selector, String name, List<Arg> args) {
		super(CommandType.CALL);
		this.setSelector(selector);
		this.name = name;
		this.args = args;
	}


	public Reference getTargetReference() {
		return targetReference;
	}


	public void setTargetReference(Reference targetReference) {
		this.targetReference = targetReference;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Arg> getArgs() {
		return args;
	}


	public void setArgs(List<Arg> args) {
		this.args = args;
	}
	
	

}
