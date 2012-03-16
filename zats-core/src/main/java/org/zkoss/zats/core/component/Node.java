package org.zkoss.zats.core.component;

import java.util.Map;
import org.zkoss.zats.core.Conversation;

/**
 * A interface represented a tree node from ZUML structure.
 * @author pao
 */
public interface Node
{
	/**
	 * get ID. of this node.
	 * @return ID or null if it hasn't.
	 */
	String getId();

	/**
	 * get type name of this node.
	 * @return type name
	 */
	String getType();

	/**
	 * get attribute by specify name.
	 * @param name attribute name.
	 * @return attribute value or null if not found or otherwise.
	 */
	Object getAttribute(String name);

	/**
	 * get all attributes of this node.
	 * @return a map of attributes.
	 */
	Map<String, Object> getAttributes();

	/**
	 * get conversation this node belonged to.
	 * @return conversation
	 */
	Conversation getConversation();
}
