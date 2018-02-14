package gov.nist.healthcare.hl7.mm.v2.domain;

public class AssignmentCommand extends Command {
	Reference reference;
	Value value;
	
	
	public String toString() {
		if(reference.getContext() == null) {
			String result = reference.getPath().toString() + " = " + value.toString();
			return result;
		}else {
			String result = reference.getContext().toString() + " :: " + reference.getPath().toString() + value.toString();
			return result;
		}
	}
	
	public AssignmentCommand(Reference selector, Reference reference, Value value) {
		super(CommandType.ASSIGNMENT);
		this.setSelector(selector);
		this.reference = reference;
		this.value = value;
	}
	
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	
	
}
