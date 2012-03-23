/* FakeSearchService.java

	Purpose:
		
	Description:
		
	History:
		2011/10/25 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.testapp.bind.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zkoss.bind.annotation.Command;
import org.zkoss.lang.Strings;

/**
 * @author dennis
 * 
 */
public class FakeOrderService implements OrderService {

	List<Order> allItems = new ArrayList<Order>();

	Random r = new Random(System.currentTimeMillis());
	
	private FakeOrderService() {
		Date now = new Date();
		for (int i=0;i<100;i++){
			allItems.add(new Order(nextOid(), "part AF2 order", nextPrice(), nextQuantity(), now));
			allItems.add(new Order(nextOid(), "part BB2 order", nextPrice(), nextQuantity(), now));
			allItems.add(new Order(nextOid(), "part CX1 order", nextPrice(), nextQuantity(), now));
			allItems.add(new Order(nextOid(), "part DS34 order", nextPrice(), nextQuantity(), now));
			allItems.add(new Order(nextOid(), "part ZK99 order", nextPrice(), nextQuantity(), now));
		}
	}
	
	static OrderService instance;
	
	static OrderService getInstance(){
		return new FakeOrderService();
	}
	
	
	
	long oid = 0;
	
	String nextOid(){
		return new DecimalFormat("00000").format(++oid);
	}

	double nextPrice() {
		return r.nextDouble()*300;
	}
	
	int nextQuantity() {
		return r.nextInt(9)+1;
	}

	public List<Order> list() {
		return new ArrayList<Order>(allItems);
	}

	public void delete(Order order) {
		allItems.remove(order);
	}

	public void save(Order order) {
		if(Strings.isBlank(order.getId())){
			order.setId(nextOid());
			allItems.add(order);
		}
	}

}
