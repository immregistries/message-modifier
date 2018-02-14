package gov.nist.healthcare.hl7.mm.v2.message.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;



import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.PresetValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.ReferenceValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Value;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ModificationDetails;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;
import gov.nist.healthcare.hl7.mm.v2.nathancode.SetCommand;

public class SimpleHL7MessageHandler implements HL7MessageHandler {


	@Override
	public String get(Reference ref, String message) throws CommandExecutionException  {
		String result;
		ReferenceParsed t = ref.toRefParsed();
		ModificationDetails modifyRequest = new ModificationDetails();
		try {
			 result = SetCommand.getValueFromHL7(message, t, modifyRequest);
		} catch (IOException e) {
			Issue issue = new Issue(IssueType.Warning,e.getMessage());
	 		throw new CommandExecutionException(issue);	
		}
		return result;
	}

	@Override
	public String set(Reference ref, String message, Value value) {
		ReferenceParsed t = ref.toRefParsed();
		String newValue = "";
		ModificationDetails modifyRequest = new ModificationDetails();

		if(value instanceof ConstantValue) {
			newValue = ((ConstantValue) value).getConstant();	

		}
		else if(value instanceof ReferenceValue) {	
				ReferenceParsed ValueReferenced = ((ReferenceValue) value).getReference().toRefParsed();
				try {
					newValue = SetCommand.getValueFromHL7(message, ValueReferenced, modifyRequest);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CommandExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
			}

		
		else if (value instanceof PresetValue) {			
		}
		else {
		}
		
			String result = "Value could not be set";
			try {
				result = SetCommand.setValueInHL7(newValue,message, t, modifyRequest);
				return result;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CommandExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;		
			
			// TODO Auto-generated catch block
		
	 
	}
	
}
