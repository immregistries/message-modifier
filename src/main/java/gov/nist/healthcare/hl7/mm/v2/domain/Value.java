package gov.nist.healthcare.hl7.mm.v2.domain;

public abstract class Value {
	private ValueType type;

	
	public Value(ValueType type) {
		super();
		this.type = type;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}
}
