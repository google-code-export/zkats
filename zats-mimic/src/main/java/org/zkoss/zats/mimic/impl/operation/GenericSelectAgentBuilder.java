/* GenericSelectAgentBuilder.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.impl.operation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.impl.ConversationCtrl;
import org.zkoss.zats.mimic.impl.au.AuUtility;
import org.zkoss.zats.mimic.impl.au.EventDataManager;
import org.zkoss.zats.mimic.operation.SelectAgent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;

/**
 *  The builder for selection agent.
 * @author pao
 */
public class GenericSelectAgentBuilder implements OperationAgentBuilder<SelectAgent> {

	public SelectAgent getOperation(final ComponentAgent target) {
		return new SingleSelectAgentImpl(target);
	}

	static class SingleSelectAgentImpl extends AgentDelegator implements SelectAgent {
		public SingleSelectAgentImpl(ComponentAgent target) {
			super(target);
		}

		public void select() {
			ComponentAgent parent = AuUtility.lookupEventTarget(target, Events.ON_SELECT);
			Set<String> items = new HashSet<String>();
			items.add(target.getUuid());

			Event event = new SelectEvent(Events.ON_SELECT, parent.getComponent(), items, target.getComponent());
			Map<String, Object> data = EventDataManager.build(event);
			((ConversationCtrl) target.getConversation()).postUpdate(parent.getUuid(), event.getName(), data);
		}

		public void deselect() {
			throw new RuntimeException(target + " can't deselect");
		}
	}
}
