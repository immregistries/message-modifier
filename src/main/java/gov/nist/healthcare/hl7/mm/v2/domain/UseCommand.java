package gov.nist.healthcare.hl7.mm.v2.domain;

public class UseCommand extends Command {
	private String key;
	private String value;
	
	public UseCommand(String key, String value) {
		super(CommandType.USE);
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
