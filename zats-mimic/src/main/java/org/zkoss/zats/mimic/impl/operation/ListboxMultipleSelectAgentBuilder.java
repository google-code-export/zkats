/* ListboxMultipleSelectAgentBuilder.java

	Purpose:
		
	Description:
		
	History:
		2012/3/20 Created by dennis

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.impl.operation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.impl.au.EventDataManager;
import org.zkoss.zats.mimic.operation.MultipleSelectAgent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

/**
 * 
 * @author dennis
 * 
 */
public class ListboxMultipleSelectAgentBuilder implements
		OperationAgentBuilder<MultipleSelectAgent> {
	public MultipleSelectAgent getOperation(final ComponentAgent target) {
		if (!target.is(Listbox.class)) {
			throw new RuntimeException("target cannot cast to listbox");
		}
		return new MultipleSelectAgentImpl(target);
	}
	
	class MultipleSelectAgentImpl extends AgentDelegator implements MultipleSelectAgent{
		public MultipleSelectAgentImpl(ComponentAgent target) {
			super(target);
		}

		public void select(int index) {
			Listbox listbox = target.as(Listbox.class);
			Set<Listitem> selitems = listbox.getSelectedItems();
			Set<String> sels = new HashSet<String>();
			for (Listitem item : selitems) {
				if (item.getIndex() == index) {
					return;// skip
				}
				sels.add(item.getUuid());
			}
			String ref = listbox.getItemAtIndex(index).getUuid();
			sels.add(ref);
			
			String cmd = Events.ON_SELECT;
			Map<String, Object> data = EventDataManager.build(new SelectEvent(cmd, target.getComponent(), sels, listbox
					.getItemAtIndex(index)));
			target.getConversation().postUpdate(target, cmd, data);
		}

		public void deselect(int index) {
			Listbox listbox = target.as(Listbox.class);
			Set<Listitem> selitems = listbox.getSelectedItems();
			Set<String> sels = new HashSet<String>();
			boolean hit = false;
			for (Listitem item : selitems) {
				if (item.getIndex() == index) {
					hit = true;
					continue;
				}
				sels.add(item.getUuid());
			}
			if (!hit)
				return;// skip
			
			String cmd = Events.ON_SELECT;
			Map<String, Object> data = EventDataManager.build(new SelectEvent(Events.ON_SELECT, target.getComponent(),
					sels, listbox.getItemAtIndex(index)));
			target.getConversation().postUpdate(target, cmd, data);
		}
	}
}
