package gov.nist.healthcare.hl7.mm.v2.domain;

public class ConstantValue extends Value {
	private String constant;

	public String toString() {
		return constant;
	}
	
	public ConstantValue(String constant) {
		super(ValueType.CONSTANT);
		this.constant = constant;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}
	
	
}
