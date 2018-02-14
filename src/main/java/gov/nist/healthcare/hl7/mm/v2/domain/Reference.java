package gov.nist.healthcare.hl7.mm.v2.domain;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;

public class Reference {
	private HL7Path path;
	private Segment context;
	
	
	public Reference(HL7Path path, Segment context) {
		super();
		this.path = path;
		this.context = context;
	}

	public ReferenceParsed toRefParsed() {		
		ReferenceParsed ref = new ReferenceParsed();
		
		ref.setSegmentName(path.getSegment().getId());
		ref.setSegementAll(path.getSegment().isAllInstances());
		ref.setSegmentRepeat(path.getSegment().getInstance());
		
		if(path.getField() != null) {
			ref.setFieldPos(path.getField().getId());
			ref.setFieldRepeatAll(path.getField().isAllInstances());
			ref.setFieldRepeat(path.getField().getInstance());
			ref.setFieldRepeatSet(path.getField().getInstance()!=0);
		}else {
			ref.setFieldRepeatAll(false);
			ref.setFieldRepeatSet(false);
		}
		
		if(path.getComponent() != null) {
			ref.setSubfieldPos(path.getComponent().getId());
			ref.setSubfieldSet(path.getComponent().isComponentSet());
		}else {
			ref.setSubfieldSet(false);
		}
		if(context  != null) {
		ref.setBoundRepeat(context.getInstance());
		ref.setBoundSegment(context.getId());
		}else {
			ref.setBoundRepeat(0);
			ref.setBoundSegment(null);
		}
		return ref;
	}
	
	
	public HL7Path getPath() {
		return path;
	}
	public void setPath(HL7Path path) {
		this.path = path;
	}
	public Segment getContext() {
		return context;
	}
	public void setContext(Segment context) {
		this.context = context;
	}
}
