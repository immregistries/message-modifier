/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nist.healthcare.hl7.mm.v2.nathancode;

/**
 *
 * @author nathan
 */
public class ReferenceParsed {

	protected String segmentName;
	protected boolean segementAll = false;
	protected int segmentRepeat = 1;

	protected int fieldPos;
	protected boolean fieldRepeatAll = false;
	protected int fieldRepeat = 1;
	protected boolean fieldRepeatSet = false;

	protected int subfieldPos;
	protected boolean subfieldSet = false;
	protected int subsubfieldPos;
	
	protected String boundSegment = null;
	protected int boundRepeat = 1;
	protected String testCaseId = null;
	
	
	@Override
	public String toString() {
		String reference = segmentName;
		if (segmentRepeat > 1)
		{
			reference += "[" + segmentRepeat + "]";
		}
		if (fieldPos > 0)
		{
			reference += "-" + fieldPos;
			if (fieldRepeatAll)
			{
				reference += "[*]";
			}
			else if (fieldRepeat > 1)
			{
				reference += "[" + fieldRepeat + "]";
			}
			if (subfieldPos > 1)
			{
				reference += "." + subfieldPos;
				if (subsubfieldPos > 1)
				{
					reference += "." + subsubfieldPos;
				}
			}
		}

		return reference;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segment) {
		this.segmentName = segment;
	}

	public int getFieldPos() {
		return fieldPos;
	}

	public void setFieldPos(int field) {
		this.fieldPos = field;
	}

	public int getSegmentRepeat() {
		return segmentRepeat;
	}

	public void setSegmentRepeat(int segmentRepeat) {
		this.segmentRepeat = segmentRepeat;
	}

	public int getFieldRepeat() {
		return fieldRepeat;
	}

	public void setFieldRepeat(int fieldRepeat) {
		this.fieldRepeat = fieldRepeat;
	}

	public boolean isFieldRepeatSet() {
		return fieldRepeatSet;
	}

	public void setFieldRepeatSet(boolean fieldRepeatSet) {
		this.fieldRepeatSet = fieldRepeatSet;
	}

	public int getSubfieldPos() {
		return subfieldPos;
	}

	public void setSubfieldPos(int subfield) {
		this.subfieldPos = subfield;
	}

	public boolean isSubfieldSet() {
		return subfieldSet;
	}

	public void setSubfieldSet(boolean subfieldSet) {
		this.subfieldSet = subfieldSet;
	}

	public int getSubsubfieldPos() {
		return subsubfieldPos;
	}

	public void setSubsubfieldPos(int subsubfield) {
		this.subsubfieldPos = subsubfield;
	}

	public boolean isFieldRepeatAll() {
		return fieldRepeatAll;
	}

	public void setFieldRepeatAll(boolean fieldAll) {
		this.fieldRepeatAll = fieldAll;
	}

	public String getBoundSegment() {
		return boundSegment;
	}

	public void setBoundSegment(String boundSegment) {
		this.boundSegment = boundSegment;
	}

	public int getBoundRepeat() {
		return boundRepeat;
	}

	public void setBoundRepeat(int boundRepeat) {
		this.boundRepeat = boundRepeat;
	}

	public boolean isSegementAll() {
		return segementAll;
	}

	public void setSegementAll(boolean all) {
		this.segementAll = all;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

}
