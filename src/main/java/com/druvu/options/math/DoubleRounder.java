package com.druvu.options.math;

/**
 * @author : Deniss Larka
 * on 10 July 2021
 **/
public final class DoubleRounder {

	private DoubleRounder() {
	}

	private static final double ROUND_SCALE_1 = 10.0d;
	private static final double ROUND_SCALE_2 = 100.0d;
	private static final double ROUND_SCALE_3 = 1000.0d;
	private static final double ROUND_SCALE_4 = 10000.0d;
	private static final double ROUND_SCALE_5 = 100000.0d;
	private static final double ROUND_SCALE_6 = 1000000.0d;

	public static double roundCents(double value) {
		return Math.round(value * ROUND_SCALE_2) / ROUND_SCALE_2;
	}

	public static double round(double value, int scale) {
		double scaleDbl = scale(scale);
		return Math.round(value * scaleDbl) / scaleDbl;
	}

	private static double scale(int scale) {
		return switch (scale) {
			case 1 -> ROUND_SCALE_1;
			case 2 -> ROUND_SCALE_2;
			case 3 -> ROUND_SCALE_3;
			case 4 -> ROUND_SCALE_4;
			case 5 -> ROUND_SCALE_5;
			case 6 -> ROUND_SCALE_6;
			default -> Math.pow(10, scale);
		};
	}

}
