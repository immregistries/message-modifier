package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.util.HashMap;
import java.util.Map;

public class ExecutionEnvironment {
	private Map<String, String> env = new HashMap<String, String>();
	
	public String getValue(String key){
		return this.env.get(key);
	}
	
	public String setValue(String key, String value){
		return this.env.put(key, value);
	}
}
