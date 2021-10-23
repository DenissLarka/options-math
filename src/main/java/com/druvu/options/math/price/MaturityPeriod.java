package com.druvu.options.math.price;

import java.time.Duration;

/**
 * Translates {@link java.time.Duration} in to years considering year as 365 days.
 *
 * @author : Deniss Larka
 * on 10 July 2021
 **/
public final class MaturityPeriod {

	private MaturityPeriod() {
	}

	public static final double HOURS_IN_A_DAY = 24D;
	public static final double DAYS_IN_YEAR = 365D;
	public static final double HOURS_IN_YEAR = HOURS_IN_A_DAY * DAYS_IN_YEAR;

	public static double yearsRough(Duration duration) {
		return duration.toHours() / HOURS_IN_YEAR;
	}

}
