package gov.nist.healthcare.hl7.mm.v2.domain;

public class ReferenceValue extends Value {
	private Reference reference;
	
	public String toString() {
		return "$" + reference.getPath().toString();
	}

	
	public ReferenceValue(Reference reference) {
		super(ValueType.REFERENCE);
		this.reference = reference;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	
}
