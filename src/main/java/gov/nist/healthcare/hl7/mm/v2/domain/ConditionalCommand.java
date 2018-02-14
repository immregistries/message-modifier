package gov.nist.healthcare.hl7.mm.v2.domain;

public class ConditionalCommand extends Command {
	private Reference reference;
	private Comparator cmp;
	private String value;
	private CallCommand call;
	
	public String toStringComparator() {
		if(cmp == Comparator.EQ) {
			return "=";
		} else if(cmp == Comparator.NE) {
			return "!=";
		} else if(cmp == Comparator.GT) {
			return ">";
		} else if(cmp == Comparator.LT) {
			return "<";
		}
		return null;
	}
	
	public String toString() {
		return "If(" + reference.getPath().toString() +" "+ toStringComparator() +" "+ value +") " + " then call " + call.toString();
	}
	
	public ConditionalCommand(Reference selector, Reference reference, Comparator cmp, String value, CallCommand call) {
		super(CommandType.IF);
		this.setSelector(selector);
		this.reference = reference;
		this.cmp = cmp;
		this.value = value;
		this.call = call;
	}


	public ConditionalCommand() {
		super(CommandType.IF);
		// TODO Auto-generated constructor stub
	}


	public Reference getReference() {
		return reference;
	}


	public void setReference(Reference reference) {
		this.reference = reference;
	}


	public Comparator getCmp() {
		return cmp;
	}


	public void setCmp(Comparator cmp) {
		this.cmp = cmp;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public CallCommand getCall() {
		return call;
	}


	public void setCall(CallCommand call) {
		this.call = call;
	}

	
}
