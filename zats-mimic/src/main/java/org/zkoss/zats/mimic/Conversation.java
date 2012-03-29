/* Conversation.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

public interface Conversation {
	/**
	 * start conversation.
	 * 
	 * @param resourceRoot
	 *            resource root path.
	 */
	void start(String resourceRoot);

	/**
	 * stop conversation.
	 */
	void stop();

	/**
	 * open specify zul page.
	 * 
	 * @param zul
	 *            the path related to the resource root path
	 */
	DesktopAgent open(String zul);

	/**
	 * clean current Desktop and release resources.
	 */
	void clean();

	DesktopAgent getDesktop();

	HttpSession getSession();

	// FIXME move this method to Conversation Controller 
	/**
	 * post an asynchronous update event.
	 * 
	 * @param target
	 *            the component agent which performed this event
	 * @param command
	 *            command
	 * @param data
	 *            data for update
	 */
	void postUpdate(ComponentAgent target, String cmd, Map<String, Object> data);
	
	/**
	 * to find the first component agent with the selector in last desktop
	 * @param selector the selector
	 * @return the first component agent, null if not found
	 */
	ComponentAgent query(String selector);
	
	/**
	 * to find the component agents with the selector in last desktop
	 * @param selector the selector
	 * @return the component agents
	 */
	List<ComponentAgent> queryAll(String selector);
}
