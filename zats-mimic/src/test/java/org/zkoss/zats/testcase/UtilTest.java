/* UtilTest.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.testcase;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.zkoss.zats.mimic.impl.Util;

public class UtilTest {

	@Test
	public void testClose() {
		Util.close(null);
		Util.close(new ByteArrayOutputStream());
	}

	@Test
	public void testParseVersion() {
		String[] versions = new String[] { "5.0.0", "5.0.1", "5.0.1.1",
				"5.0.2", "5.0.2.1", "6.0.0", "5.0.10", "5.0.9", "3.6.3",
				"3.6.2", "3.6.4", "5.0.8", "5.0.7.1", "5.0.7", "5.0.6",
				"5.0.5", "5.0.4", "5.0.3" };
		String[] correct = new String[] { "3.6.2", "3.6.3", "3.6.4", "5.0.0",
				"5.0.1", "5.0.1.1", "5.0.2", "5.0.2.1", "5.0.3", "5.0.4",
				"5.0.5", "5.0.6", "5.0.7", "5.0.7.1", "5.0.8", "5.0.9",
				"5.0.10", "6.0.0" };
		List<BigInteger> list = new ArrayList<BigInteger>();
		for (String version : versions)
			list.add(Util.parseVersion(version));
		Collections.sort(list);
		System.out.println(list);
		for (int i = 0; i < versions.length; ++i)
			versions[i] = list.get(i).toString();
		assertArrayEquals(correct, versions);

		assertEquals(
				0,
				Util.parseVersion(" 6.0.0  ").compareTo(
						Util.parseVersion("\t6.0.0  ")));
		assertEquals(
				1,
				Util.parseVersion(" 6.0.0").compareTo(
						Util.parseVersion("  5.1.1 ")));
		assertNull(Util.parseVersion("dfasdflj"));
		assertNull(Util.parseVersion(null));
		assertNull(Util.parseVersion(""));
		assertNull(Util.parseVersion(" "));
	}
}
