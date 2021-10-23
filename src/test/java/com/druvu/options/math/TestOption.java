package com.druvu.options.math;

import static com.druvu.options.math.profit.MarketSide.SELL;
import static com.druvu.options.math.OptionSide.CALL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.druvu.options.math.profit.MarketSide;
import com.druvu.options.math.profit.OptionProfitMath;
import com.druvu.options.math.profit.OptionProfitRequest;

/**
 * @author Deniss Larka
 * on 10 April 2021
 */
public class TestOption {

	@Test
	public void test() {
		//		Assert.assertEquals(calc(BUY, CALL, 290, 9, 1, 299), 0);
		//		Assert.assertEquals(calc(BUY, CALL, 45, 2.88, 1, 49.17), 1.29);
		//		Assert.assertEquals(calc(BUY, CALL, 45, 2.88, 1, 41), -2.88);

		Assert.assertEquals(calc(SELL, CALL, 45, 2.88, 1, 45.17), 2.71);

	}

	double calc(
			final MarketSide direction,
			final OptionSide side,
			final double strike,
			final double premium,
			final int numberOfOptions,
			final double spot) {
		OptionProfitRequest option = new OptionProfitRequest(direction, side, strike, premium, numberOfOptions);
		return OptionProfitMath.profitRound(option, spot);

	}

}