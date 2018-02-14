package gov.nist.healthcare.hl7.mm.v2.functions;

import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public abstract class Function {
	protected HL7MessageHandler messageHandler;
	private String name;
	
	
    public Function(HL7MessageHandler messageHandler, String name) {
		super();
		this.messageHandler = messageHandler;
		this.name = name;
	}
    
	public abstract void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException;
	
    public String getArgValue(List<Arg> arguments, String argName) {
     Arg arg = 	arguments.stream().filter(x -> x.getName().equals(argName)).findFirst().orElse(null);
     if(arg != null) {
    	 	return arg.getValue().getConstant();
    	 	}
     else {
    	 return "";
     }
     
    }
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Function other = (Function) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
    
}
