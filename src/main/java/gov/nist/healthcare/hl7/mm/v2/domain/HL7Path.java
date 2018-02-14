package gov.nist.healthcare.hl7.mm.v2.domain;

public class HL7Path {
	private Segment segment;
	private Field field;
	private Component component;
	private SubComponent subComponent;
	
	
	public HL7Path(Segment segment, Field field, Component component, SubComponent subComponent) {
		super();
		this.segment = segment;
		this.field = field;
		this.component = component;
		this.subComponent = subComponent;
	}
	
	public Segment getSegment() {
		return segment;
	}
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public SubComponent getSubComponent() {
		return subComponent;
	}
	public void setSubComponent(SubComponent subComponent) {
		this.subComponent = subComponent;
	}
	
	public String toString(){
		String path = "";
		if(this.segment == null){
			return path;
		}
		else {
			path = this.segment.toString();
		}
		
		if(this.field == null){
			return path;
		}
		else {
			path += "-" + this.field.toString();
		}
		
		if(this.component == null){
			return path;
		}
		else {
			path += "." + this.component.toString();
		}
		
		if(this.subComponent == null){
			return path;
		}
		else {
			path += "." + this.subComponent.toString();
		}
		
		return path;
	}
}
