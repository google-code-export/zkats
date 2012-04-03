/* BasicAgentTest.java

	Purpose:
		
	Description:
		
	History:
		2012/3/22 Created by dennis

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zats.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zats.mimic.AgentException;
import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.Conversations;
import org.zkoss.zats.mimic.operation.CheckAgent;
import org.zkoss.zats.mimic.operation.ClickAgent;
import org.zkoss.zats.mimic.operation.FocusAgent;
import org.zkoss.zats.mimic.operation.KeyStrokeAgent;
import org.zkoss.zats.mimic.operation.OpenAgent;
import org.zkoss.zats.mimic.operation.SelectAgent;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Treeitem;

/**
 * @author dennis
 *
 */
public class BasicAgentTest {
	@BeforeClass
	public static void init()
	{
//		Conversations.start("./src/test/resources");
		Conversations.start(".");
	}

	@AfterClass
	public static void end()
	{
		Conversations.stop();
	}

	@After
	public void after()
	{
		Conversations.clean();
	}
	
	
	@Test
	public void testKeyStrokeAgent(){
		Conversations.open("/~./basic/keystroke.zul");
		
		ComponentAgent inp1 = Conversations.query("#inp1");
		ComponentAgent inp2 = Conversations.query("#inp2");
		ComponentAgent l1 = Conversations.query("#l1");
		
		Assert.assertEquals("", l1.as(Label.class).getValue());
		
		inp1.stroke("#enter");
		Assert.assertEquals("ENTER key is pressed", l1.as(Label.class).getValue());
		
		inp1.stroke("#esc");
		Assert.assertEquals("ESC key is pressed", l1.as(Label.class).getValue());
		
		inp1.stroke("^a");
		Assert.assertEquals("Ctrl+A is pressed,alt:false,ctrl:true,shift:false", l1.as(Label.class).getValue());
		
		inp1.stroke("@b");
		Assert.assertEquals("Alt+B is pressed,alt:true,ctrl:false,shift:false", l1.as(Label.class).getValue());
		
		inp1.stroke("#f8");
		Assert.assertEquals("F8 is pressed,alt:false,ctrl:false,shift:false", l1.as(Label.class).getValue());
		
		
		inp2.stroke("#right");
		Assert.assertEquals("keyCode:39 is pressed,alt:false,ctrl:false,shift:false", l1.as(Label.class).getValue());
		
		inp2.as(KeyStrokeAgent.class).stroke("$#left");
		Assert.assertEquals("keyCode:37 is pressed,alt:false,ctrl:false,shift:true", l1.as(Label.class).getValue());
		
		try{
			inp1.as(KeyStrokeAgent.class).stroke("^a#right");//2 key
			Assert.fail("should not go here");
		}catch(AgentException x){}
		
		try{
			inp1.as(KeyStrokeAgent.class).stroke("^");//no keycode
			Assert.fail("should not go here");
		}catch(AgentException x){}
		
	}
	
	@Test
	public void testTypeAgent1(){
		Conversations.open("/~./basic/type1.zul");
		
		ComponentAgent l = Conversations.query("#l1");
		ComponentAgent inp = Conversations.query("#inp1");
		//bandbox
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("A");
		Assert.assertEquals("A",l.as(Label.class).getValue());
		
		inp.type("");
		Assert.assertEquals("A",l.as(Label.class).getValue());
		
		inp.type("B");
		Assert.assertEquals("B",l.as(Label.class).getValue());
		
		//combobox
		l = Conversations.query("#l2");
		inp = Conversations.query("#inp2");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("C");
		Assert.assertEquals("C",l.as(Label.class).getValue());
		inp.type("");
		Assert.assertEquals("C",l.as(Label.class).getValue());
		inp.type("D");
		Assert.assertEquals("D",l.as(Label.class).getValue());
		
		
		//textbox
		l = Conversations.query("#l10");
		inp = Conversations.query("#inp10");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("E");
		Assert.assertEquals("E",l.as(Label.class).getValue());
		inp.type("");
		Assert.assertEquals("E",l.as(Label.class).getValue());
		inp.type("F");
		Assert.assertEquals("F",l.as(Label.class).getValue());
		
		//decimalbox
		l = Conversations.query("#l4");
		inp = Conversations.query("#inp4");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("1");
		Assert.assertEquals("1.0",l.as(Label.class).getValue());
		inp.type("-1");
		Assert.assertEquals("1.0",l.as(Label.class).getValue());

		inp.type("2.33");
		Assert.assertEquals("2.33",l.as(Label.class).getValue());
		
		//doublebox
		l = Conversations.query("#l5");
		inp = Conversations.query("#inp5");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("3");
		Assert.assertEquals("3.0",l.as(Label.class).getValue());
		inp.type("-3");
		Assert.assertEquals("3.0",l.as(Label.class).getValue());
		inp.type("4.33");
		Assert.assertEquals("4.33",l.as(Label.class).getValue());
		
		//doublespinner
		l = Conversations.query("#l6");
		inp = Conversations.query("#inp6");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("5");
		Assert.assertEquals("5.0",l.as(Label.class).getValue());

		inp.type("-5");
		Assert.assertEquals("5.0",l.as(Label.class).getValue());

		inp.type("6.33");
		Assert.assertEquals("6.33",l.as(Label.class).getValue());
		
		//intbox
		l = Conversations.query("#l7");
		inp = Conversations.query("#inp7");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("7");
		Assert.assertEquals("7",l.as(Label.class).getValue());
		inp.type("-7");
		Assert.assertEquals("7",l.as(Label.class).getValue());
		inp.type("8");
		Assert.assertEquals("8",l.as(Label.class).getValue());
		
		
		
		
		//longbox
		l = Conversations.query("#l8");
		inp = Conversations.query("#inp8");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("9");
		Assert.assertEquals("9",l.as(Label.class).getValue());
		inp.type("-9");
		Assert.assertEquals("9",l.as(Label.class).getValue());
		inp.type("10");
		Assert.assertEquals("10",l.as(Label.class).getValue());
		
		
		
		//spinner
		l = Conversations.query("#l9");
		inp = Conversations.query("#inp9");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("11");
		Assert.assertEquals("11",l.as(Label.class).getValue());
		inp.type("-11");
		Assert.assertEquals("11",l.as(Label.class).getValue());
		inp.type("12");
		Assert.assertEquals("12",l.as(Label.class).getValue());
		
		//datebox
		l = Conversations.query("#l3");
		inp = Conversations.query("#inp3");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("20120223");
		Assert.assertEquals("20120223",l.as(Label.class).getValue());
		inp.type("20110101");
		Assert.assertEquals("20120223",l.as(Label.class).getValue());
		inp.type("20120320");
		Assert.assertEquals("20120320",l.as(Label.class).getValue());
		
		//timebox
		l = Conversations.query("#l11");
		inp = Conversations.query("#inp11");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("13:00");
		Assert.assertEquals("13:00",l.as(Label.class).getValue());
		inp.type("10:00");
		Assert.assertEquals("13:00",l.as(Label.class).getValue());
		inp.type("14:02");
		Assert.assertEquals("14:02",l.as(Label.class).getValue());
	}
	
	@Test
	public void testTypeAgent2(){
		Conversations.open("/~./basic/type2.zul");
		
		ComponentAgent l = Conversations.query("#l1");
		ComponentAgent inp = Conversations.query("#inp1");
		//bandbox
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("A");
		Assert.assertEquals("A",l.as(Label.class).getValue());
		
		inp.type("");
		Assert.assertEquals("A",l.as(Label.class).getValue());
		
		inp.type("B");
		Assert.assertEquals("B",l.as(Label.class).getValue());
		
		//combobox
		l = Conversations.query("#l2");
		inp = Conversations.query("#inp2");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("C");
		Assert.assertEquals("C",l.as(Label.class).getValue());
		inp.type("");
		Assert.assertEquals("C",l.as(Label.class).getValue());
		inp.type("D");
		Assert.assertEquals("D",l.as(Label.class).getValue());
		
		
		//textbox
		l = Conversations.query("#l10");
		inp = Conversations.query("#inp10");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("E");
		Assert.assertEquals("E",l.as(Label.class).getValue());
		inp.type("");
		Assert.assertEquals("E",l.as(Label.class).getValue());
		inp.type("F");
		Assert.assertEquals("F",l.as(Label.class).getValue());
		
		//decimalbox
		l = Conversations.query("#l4");
		inp = Conversations.query("#inp4");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("1");
		Assert.assertEquals("1.0",l.as(Label.class).getValue());
		inp.type("-1");
		Assert.assertEquals("1.0",l.as(Label.class).getValue());

		inp.type("2,222.33");
		Assert.assertEquals("2222.33",l.as(Label.class).getValue());
		
		//doublebox
		l = Conversations.query("#l5");
		inp = Conversations.query("#inp5");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("3");
		Assert.assertEquals("3.0",l.as(Label.class).getValue());
		inp.type("-3");
		Assert.assertEquals("3.0",l.as(Label.class).getValue());
		inp.type("4,444.33");
		Assert.assertEquals("4444.33",l.as(Label.class).getValue());
		
		//doublespinner
		l = Conversations.query("#l6");
		inp = Conversations.query("#inp6");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("5");
		Assert.assertEquals("5.0",l.as(Label.class).getValue());

		inp.type("-5");
		Assert.assertEquals("5.0",l.as(Label.class).getValue());

		inp.type("6,666.33");
		Assert.assertEquals("6666.33",l.as(Label.class).getValue());
		
		//intbox
		l = Conversations.query("#l7");
		inp = Conversations.query("#inp7");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("7");
		Assert.assertEquals("7",l.as(Label.class).getValue());
		inp.type("-7");
		Assert.assertEquals("7",l.as(Label.class).getValue());
		inp.type("8,888");
		Assert.assertEquals("8888",l.as(Label.class).getValue());
		
		
		
		
		//longbox
		l = Conversations.query("#l8");
		inp = Conversations.query("#inp8");
		Assert.assertEquals("",l.as(Label.class).getValue());
		
		inp.type("9");
		Assert.assertEquals("9",l.as(Label.class).getValue());
		inp.type("-9");
		Assert.assertEquals("9",l.as(Label.class).getValue());
		inp.type("1,110");
		Assert.assertEquals("1110",l.as(Label.class).getValue());
		
		
		
		//spinner
		l = Conversations.query("#l9");
		inp = Conversations.query("#inp9");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("11");
		Assert.assertEquals("11",l.as(Label.class).getValue());
		inp.type("-11");
		Assert.assertEquals("11",l.as(Label.class).getValue());
		inp.type("1,112");
		Assert.assertEquals("1112",l.as(Label.class).getValue());
		
		//datebox
		l = Conversations.query("#l3");
		inp = Conversations.query("#inp3");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("23022012");
		Assert.assertEquals("20120223",l.as(Label.class).getValue());
		inp.type("01012011");
		Assert.assertEquals("20120223",l.as(Label.class).getValue());
		inp.type("20032012");
		Assert.assertEquals("20120320",l.as(Label.class).getValue());
		
		//timebox
		l = Conversations.query("#l11");
		inp = Conversations.query("#inp11");
		Assert.assertEquals("",l.as(Label.class).getValue());
		inp.type("00:13");
		Assert.assertEquals("13:00",l.as(Label.class).getValue());
		inp.type("00:10");
		Assert.assertEquals("13:00",l.as(Label.class).getValue());
		inp.type("02:14");
		Assert.assertEquals("14:02",l.as(Label.class).getValue());
	}
	
	@Test
	public void testOpenAgentTree(){
		Conversations.open("/~./basic/open-tree.zul");
		
		ComponentAgent tree = Conversations.query("#tree");
		List<ComponentAgent> items = tree.queryAll("treeitem");
		Assert.assertEquals(2, items.size());
		
		Stack<ComponentAgent> stack = new Stack<ComponentAgent>();
		stack.addAll(items);
		
		while(!stack.empty()){
			ComponentAgent item = stack.pop();
			
			if(item.query("treechildren")!=null){
				Assert.assertFalse(item.as(Treeitem.class).isOpen());
				items = item.query("treechildren").queryAll("treeitem");//the sub-treeitem.
				Assert.assertEquals(0, items.size());
				
				item.as(OpenAgent.class).open(true);//trigger open to load the tree item.
				
				Assert.assertTrue(item.as(Treeitem.class).isOpen());
				items = item.query("treechildren").queryAll("treeitem");//the sub-treeitem.
				Assert.assertEquals(2, items.size());
				for(ComponentAgent si:items){
					stack.push(si);
				}
			}
		}
		
		items = tree.queryAll("treeitem");
		Assert.assertEquals(14, items.size());
	}
	
	@Test
	public void testFocusAgent() {
		Conversations.open("/~./basic/focus.zul");
		Label curr = Conversations.query("#current").as(Label.class);
		Label lost = Conversations.query("#lost").as(Label.class);
		assertTrue(curr.getValue().length() <= 0);
		assertTrue(curr.getValue().length() <= 0);

		for (int i = 1; i <= 11; ++i) {
			ComponentAgent comp = Conversations.query("#c" + i);
			comp.as(FocusAgent.class).focus();
			String name = comp.as(AbstractComponent.class).getDefinition().getName();
			assertEquals(name, curr.getValue());
			comp.as(FocusAgent.class).blur();
			assertEquals(name, lost.getValue());
		}
	}

	@Test
	public void testCheckAgent() {
		Conversations.open("/~./basic/check.zul");

		// validate msg
		Label msg = Conversations.query("#msg").as(Label.class);
		assertTrue(msg.getValue().length() <= 0);

		// test checkbox and menuitem
		String label = "";
		for (int i = 1; i <= 6; ++i) {
			Conversations.query("#c" + i).as(CheckAgent.class).check(true);
			label += "c" + i + " ";
			assertEquals(label, msg.getValue());
		}
		// test radiogroup
		for (int i = 7; i <= 9; ++i) {
			Conversations.query("#c" + i).as(CheckAgent.class).check(true);
			assertEquals(label + "c" + i + " ", msg.getValue());
		}
	}

	@Test
	public void testClickAgent() {
		Conversations.open("/~./basic/click.zul");
		assertEquals("Hello World!", Conversations.query("#msg").as(Label.class).getValue());
		Conversations.query("#btn").as(ClickAgent.class).click();
		assertEquals("Welcome", Conversations.query("#msg").as(Label.class).getValue());
	}
	
	@Test
	public void testMultipleSelect() {
		Conversations.open("/~./basic/multiple-select.zul");

		Label msg = Conversations.query("#msg").as(Label.class);
		assertEquals("", msg.getValue());

		ComponentAgent listbox = Conversations.query("#lb");
		assertEquals(4, listbox.as(Listbox.class).getChildren().size()); // include header
		List<ComponentAgent> items = listbox.queryAll("listitem");

		// multiple selection
		items.get(0).as(SelectAgent.class).select();
		assertEquals("[i0]", msg.getValue());
		assertEquals(1, listbox.as(Listbox.class).getSelectedCount());
		items.get(1).as(SelectAgent.class).select();
		assertEquals("[i0, i1]", msg.getValue());
		assertEquals(2, listbox.as(Listbox.class).getSelectedCount());
		items.get(2).as(SelectAgent.class).select();
		assertEquals("[i0, i1, i2]", msg.getValue());
		assertEquals(3, listbox.as(Listbox.class).getSelectedCount());
		items.get(1).as(SelectAgent.class).deselect();
		assertEquals("[i0, i2]", msg.getValue());
		assertEquals(2, listbox.as(Listbox.class).getSelectedCount());
		items.get(0).as(SelectAgent.class).deselect();
		assertEquals("[i2]", msg.getValue());
		assertEquals(1, listbox.as(Listbox.class).getSelectedCount());
		items.get(2).as(SelectAgent.class).deselect();
		assertEquals("[]", msg.getValue());
		assertEquals(0, listbox.as(Listbox.class).getSelectedCount());
		items.get(2).as(SelectAgent.class).deselect(); // should happen nothing
		assertEquals("[]", msg.getValue());
		assertEquals(0, listbox.as(Listbox.class).getSelectedCount());
		
		// single selection
		Conversations.query("#sbtn").as(ClickAgent.class).click();
		String[] values = { "[i0]", "[i1]", "[i2]" };
		for (int i = 0; i < 3; ++i) {
			items.get(i).as(SelectAgent.class).select();
			assertEquals(values[i], msg.getValue());
		}
	}
	
	@Test
	public void testSelect()
	{
		// TODO 
	}
}
