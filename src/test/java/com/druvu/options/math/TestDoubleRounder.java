package com.druvu.options.math;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author : Deniss Larka
 * on 10 July 2021
 **/
public class TestDoubleRounder {

	@Test
	public void testRound() {
		Assert.assertEquals(DoubleRounder.round(111.11111111d, 3), 111.111d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, 2), 111.11d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, 1), 111.1d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, 0), 111.0d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, -1), 110.0d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, -2), 100.0d);
		Assert.assertEquals(DoubleRounder.round(111.11111111d, -3), 0.0d);
		Assert.assertEquals(DoubleRounder.round(1111.11111111d, -3), 1000.0d);

	}
}