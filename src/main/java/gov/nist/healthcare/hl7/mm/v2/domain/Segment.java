package gov.nist.healthcare.hl7.mm.v2.domain;

public class Segment {
	
	private String id;
	private int instance;
	private boolean allInstances = false;
	
	
	public Segment(String id, String instance) {
		super();
		this.id = id;
		if(instance.equals("*")) {
			allInstances = true;
		}
		else {
			this.instance = Integer.parseInt(instance);
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInstance() {
		return instance;
	}
	public void setInstance(int instance) {
		this.instance = instance;
	}
	
	public boolean isAllInstances() {
		return allInstances;
	}

	public void setAllInstances(boolean allInstances) {
		this.allInstances = allInstances;
	}

	public String toString(){
		return id + (allInstances ? "[*]" :  "[" + instance + "]" );  
	}
}
