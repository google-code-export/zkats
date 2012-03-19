// ported from zk 6.0.0 
// original package: org.zkoss.zk.ui.select
package org.zkoss.zats.select.impl;

import org.zkoss.zk.ui.UiException;

/**
 * Exception thrown for illegal selector string.
 * @since 6.0.0
 * @author simonpai
 */
public class ParseException extends UiException {
	
	private static final long serialVersionUID = -3279019270343851540L;
	
	public ParseException(int step, Object state, Character input) {
		super("Illegal selector syntax: unexpected character '" + input + 
				"' at index " + (step-1));
	}
	
	public ParseException(String message){
		super(message);
	}
	
}
