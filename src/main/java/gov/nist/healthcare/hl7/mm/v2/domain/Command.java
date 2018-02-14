package gov.nist.healthcare.hl7.mm.v2.domain;

public class Command {
	private CommandType type;
	private Reference selector;

	public Command(CommandType type) {
		super();
		this.type = type;
	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	public Reference getSelector() {
		return selector;
	}

	public void setSelector(Reference selector) {
		this.selector = selector;
	}

}
