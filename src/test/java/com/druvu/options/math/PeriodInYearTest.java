package com.druvu.options.math;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.druvu.options.math.price.MaturityPeriod;

/**
 * @author Deniss Larka
 * on 20 April 2021
 */
public class PeriodInYearTest {

	@Test
	public void test() {
		Assert.assertEquals(DoubleRounder.round(MaturityPeriod.yearsRough(Duration.ofHours(158)), 6), 0.018037d);
	}
}
