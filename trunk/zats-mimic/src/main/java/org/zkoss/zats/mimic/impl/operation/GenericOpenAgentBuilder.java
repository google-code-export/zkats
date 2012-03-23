/* GenericOpenAgentBuilder.java

	Purpose:
		
	Description:
		
	History:
		2012/3/22 Created by dennis

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zats.mimic.impl.operation;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.operation.OpenAgent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;

/**
 * @author dennis
 *
 */
public class GenericOpenAgentBuilder implements OperationAgentBuilder<OpenAgent> {
	public OpenAgent getOperation(final ComponentAgent target) {
		return new OpenAgentImpl(target);
	}
	class OpenAgentImpl extends AgentDelegator implements OpenAgent{
		public OpenAgentImpl(ComponentAgent target) {
			super(target);
		}

		public void open(boolean open) {
			OpenEvent evt = new OpenEvent(Events.ON_OPEN,target.getComponent(),open,null,null);
			AuUtility2.postUpdate(target, evt);
		}
	}

}