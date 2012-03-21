/* ClickAgent.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.operation;

public interface ClickAgent extends OperationAgent {
	
	/**
	 * click on this component
	 */
	void click();

	/**
	 * double click on this component
	 */
	void doubleClick();

	/**
	 * right click on this component
	 */
	void rightClick();
}
