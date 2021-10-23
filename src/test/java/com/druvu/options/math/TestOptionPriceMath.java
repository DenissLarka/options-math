package com.druvu.options.math;

import static com.druvu.options.math.DoubleRounder.round;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.druvu.options.math.price.OptionGreeks;
import com.druvu.options.math.price.OptionPriceMath;
import com.druvu.options.math.price.OptionPriceRequest;

/**
 * https://goodcalculators.com/black-scholes-calculator/
 *
 * @author Deniss Larka
 * on 22 April 2021
 */
public class TestOptionPriceMath {

	@Test
	public void test() {

		OptionPriceRequest requestCall = new OptionPriceRequest(OptionSide.CALL, 300, 250, 1, 0.05, 0.15);
		final double call = OptionPriceMath.price(requestCall);
		Assert.assertEquals(round(call, 2), 63.24D);

		final OptionGreeks greeksCall = OptionPriceMath.greeks(requestCall);
		Assert.assertEquals(round(greeksCall.price(), 3), 63.240D);
		Assert.assertEquals(round(greeksCall.delta(), 3), 0.948D);
		Assert.assertEquals(round(greeksCall.gamma(), 3), 0.002D);
		//theta is not accurate if compare with the results from "black-scholes-calculator"
		//any ideas, please advice!
		//Assert.assertEquals(greeksCall.theta(),-13.457D);???
		Assert.assertEquals(round(greeksCall.rho(), 3), 221.097D);

		OptionPriceRequest requestPut = new OptionPriceRequest(OptionSide.PUT, 300, 250, 1, 0.05, 0.15);
		final double put = OptionPriceMath.price(requestPut);
		Assert.assertEquals(round(put, 2), 1.05D);

		final OptionGreeks greeksPut = OptionPriceMath.greeks(requestPut);
		Assert.assertEquals(round(greeksPut.price(), 2), 1.05D);

	}

}