package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;
import gov.nist.healthcare.hl7.mm.v2.domain.Arg;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class ClearFunction extends Function {
	
	public ClearFunction(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) throws CommandExecutionException{

//		if(selector == null) {
//			String s = "";
//			context.getMessage(). = s;
//		}
//		System.out.println(selector);

	    ReferenceParsed targetReference = selector.toRefParsed();
	    String resultText = context.getMessage();
	    resultText = clearValueInHL7(resultText, targetReference);
	    context.setMessage(resultText);
	  
}
	
	public static String clearValueInHL7(String resultText, ReferenceParsed t) throws CommandExecutionException {
		System.out.println(t.getSubfieldPos());
		

	    BufferedReader inResult = new BufferedReader(new StringReader(resultText));
	    boolean foundBoundStart = false;
	    boolean foundBoundEnd = false;
	    int boundCount = 0;
	    resultText = "";
	    String lineResult;
	    int repeatCount = 0;
	    try {
			while ((lineResult = inResult.readLine()) != null) {
			  lineResult = lineResult.trim();
			  if (lineResult.length() > 0) {
			    if (t.getBoundSegment() != null && !foundBoundEnd) {
			      boolean skip = false;
			      if (lineResult.startsWith(t.getBoundSegment() + "|")) {
			        boundCount++;
			        if (!foundBoundStart) {
			          if (boundCount == t.getBoundRepeat()) {
			            foundBoundStart = true;
			          }
			        } else if (foundBoundStart) {
			          foundBoundEnd = true;
			        }
			        skip = true;
			      } else if (foundBoundStart) {
			        if (!lineResult.startsWith(t.getSegmentName() + "|")) {
			          skip = true;
			        }
			      } else {
			        skip = true;
			      }
			      if (skip) {
			        resultText += lineResult + "\r";
			        continue;
			      }
			    }
			    if (lineResult.startsWith(t.getSegmentName() + "|")) {
			      repeatCount++;
			      if (t.getSegmentRepeat() == repeatCount) {
			        if (t.getFieldPos() == 0) {
			          lineResult = t.getSegmentName() + "|";
			        } else {
			          int pos = lineResult.indexOf("|");
			          int count = (lineResult.startsWith("MSH|") || lineResult.startsWith("FHS|")
			              || lineResult.startsWith("BHS|")) ? 2 : 1;
			          while (pos != -1 && count < t.getFieldPos()) {
			            pos = lineResult.indexOf("|", pos + 1);
			            count++;
			          }
			          if (pos != -1) {
			            if (!t.isFieldRepeatSet() && !t.isSubfieldSet()) {
			              int endPosBar = lineResult.indexOf("|", pos + 1);
			              if (endPosBar == -1) {
			                lineResult = lineResult.substring(0, pos) + "|";
			              } else {
			                lineResult = lineResult.substring(0, pos + 1) + lineResult.substring(endPosBar);
			              }
			            } else {
			              boolean isMSH2 = ((lineResult.startsWith("MSH|") || lineResult.startsWith("FHS|")
			                  || lineResult.startsWith("BHS|"))) && t.getFieldPos() == 2;
			              count = 1;
			              pos++;
			              int tildePos = pos;
			              while (tildePos != -1 && count < t.getFieldRepeat()) {
			                int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("^", tildePos);
			                int endPosBar = lineResult.indexOf("|", tildePos);
			                if (endPosBar == -1) {
			                  endPosBar = lineResult.length();
			                }
			                if (endPosTilde == -1 || endPosTilde >= endPosBar) {
			                  tildePos = -1;
			                  pos = endPosBar;
			                } else {
			                  tildePos = endPosTilde + 1;
			                  pos = tildePos;
			                  count++;
			                }
			              }
			              if (tildePos != -1) {
			                if (!t.isSubfieldSet()) {
			                  int endPosBar = lineResult.indexOf("|", pos);
			                  if (endPosBar == -1) {
			                    endPosBar = lineResult.length();
			                  }
			                  int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("^", pos);
			                  if (endPosTilde == -1) {
			                    endPosTilde = lineResult.length();
			                  }
			                  if (endPosTilde < endPosBar) {
			                    lineResult =
			                        lineResult.substring(0, pos) + lineResult.substring(endPosTilde);
			                  } else {
			                    lineResult = lineResult.substring(0, pos) + lineResult.substring(endPosBar);
			                  }
			                } else if (t.getSubfieldPos() == 0) {
			                  int endPosBar = lineResult.indexOf("|", pos);
			                  if (endPosBar == -1) {
			                    endPosBar = lineResult.length();
			                  }
			                  int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
			                  if (endPosTilde == -1) {
			                    endPosTilde = lineResult.length();
			                  }
			                  if (endPosTilde < endPosBar) {
			                    lineResult =
			                        lineResult.substring(0, pos) + lineResult.substring(endPosTilde);
			                  } else {
			                    lineResult = lineResult.substring(0, pos) + lineResult.substring(endPosBar);
			                  }
			                } else {
			                  count = 1;
			                  while (pos != -1 && count < t.getSubfieldPos()) {
			                    int posCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
			                    int endPosBar = lineResult.indexOf("|", pos);
			                    if (endPosBar == -1) {
			                      endPosBar = lineResult.length();
			                    }
			                    int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
			                    if (endPosTilde == -1) {
			                      endPosTilde = lineResult.length();
			                    }
			                    if (posCaret == -1 || (posCaret > endPosBar || posCaret > endPosTilde)) {
			                      pos = -1;
			                      break;
			                    } else {
			                      pos = posCaret + 1;
			                    }
			                    count++;
			                  }
			                  if (pos != -1) {
			                    int endPosBar = lineResult.indexOf("|", pos);
			                    if (endPosBar == -1) {
			                      endPosBar = lineResult.length();
			                    }
			                    int endPosCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
			                    int endPosRepeat = isMSH2 ? -1 : lineResult.indexOf("~", pos);
			                    int endPos = endPosBar;
			                    if (endPosRepeat != -1 && endPosRepeat < endPos) {
			                      endPos = endPosRepeat;
			                    }
			                    if (endPosCaret != -1 && endPosCaret < endPos) {
			                      endPos = endPosCaret;
			                    }
			                    lineResult = lineResult.substring(0, pos) + lineResult.substring(endPos);
			                  }
			                }
			              }
			            }
			          }
			        }
			      }
			    }
			    resultText += lineResult + "\r";
			  }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return resultText;
	  }

}