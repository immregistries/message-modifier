package gov.nist.healthcare.hl7.mm.v2.domain;

public class PresetValue extends Value {
	private String id;

	
	public PresetValue(String id) {
		super(ValueType.PRESET);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
