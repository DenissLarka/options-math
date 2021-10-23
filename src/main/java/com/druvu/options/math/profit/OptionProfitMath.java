package com.druvu.options.math.profit;

import com.druvu.options.math.DoubleRounder;

/**
 * @author Deniss Larka
 * on 28 September 2020
 */
public final class OptionProfitMath {

	private OptionProfitMath() {
	}

	public static double profit(OptionProfitRequest request, double spot) {
		double cleanReturn = request.isCall() ? spot - request.strike() : request.strike() - spot;
		if (cleanReturn < 0) {
			cleanReturn = 0;
		}
		final double direction = request.isBuy() ? 1 : -1;
		return (cleanReturn - request.premium()) * request.numberOfOptions() * direction;

	}

	public static double profitRound(OptionProfitRequest request, double spot) {
		return DoubleRounder.roundCents(profit(request, spot));
	}

}
