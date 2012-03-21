/* GridRendererAgentBuilder.java

	Purpose:
		
	Description:
		
	History:
		2012/3/20 Created by dennis

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.impl.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.operation.RendererAgent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.impl.LoadStatus;
/**
 * 
 * @author dennis
 *
 */
public class GridRendererAgentBuilder implements OperationAgentBuilder<RendererAgent> {
	public RendererAgent getOperation(final ComponentAgent target) {
		if(!target.is(Grid.class)){
			throw new RuntimeException("target "+target+" cannot transfer to Grid");
		}
		return new RendererAgentImpl(target);
	}
	
	class RendererAgentImpl extends AgentDelegator implements RendererAgent{
		public RendererAgentImpl(ComponentAgent target) {
			super(target);
		}

		public void render(int x, int y) {
			Grid grid = target.as(Grid.class);
			Rows rows = grid.getRows();
			if(rows==null) return;
			
			List<Component> children = rows.getChildren();
			if(x==-1) x = 0;
			if(y==-1) y = rows.getChildren().size()-1;
			ArrayList<String> ids = new ArrayList<String>();
			while(true){
				if(x > y) break;
				Row r = (Row)children.get(x++);
				if(r!=null && !isLoaded(r)){//damn, isLoaded is not open in row
					ids.add(r.getUuid());
				}
			}
			if(ids.size()==0) return;
			AuUtility.postOnRender(target, ids.toArray(new String[ids.size()]));
		};
	}
	
	static boolean ignoreIsLoaded = false;
	
	private static boolean isLoaded(Row r){
		Object ctrl = r.getExtraCtrl();
		if(ctrl instanceof LoadStatus){
			return ((LoadStatus)ctrl).isLoaded();
		}
		return false;
	}
}
