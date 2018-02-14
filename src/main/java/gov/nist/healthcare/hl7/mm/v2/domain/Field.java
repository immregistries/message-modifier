package gov.nist.healthcare.hl7.mm.v2.domain;

public class Field {
	private int id;
	private int instance = 0;
	private boolean allInstances = false;
	
	
	public Field(String id, String instance) {
		super();
		this.id = Integer.parseInt(id);
		if(instance.equals("*")) {
			allInstances = true;
		}
		else {
			this.instance = Integer.parseInt(instance);
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return id +  (allInstances ? "[*]" :  "[" + instance + "]" );  
	}
}
