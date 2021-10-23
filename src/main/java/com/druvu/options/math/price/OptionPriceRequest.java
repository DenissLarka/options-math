package com.druvu.options.math.price;

import com.druvu.options.math.OptionSide;

/**
 * -------------------
 * optionSide       = CALL/PUT
 * spotPrice        = Spot price of underlying stock/asset
 * strike           = Strike price
 * riskFreeInterest = Risk free annual interest rate continuously compounded
 * yearsToMaturity  = Time in years until option expiration (maturity)
 * volatility       = Implied volatility of returns of underlying stock/asset
 * -------------------
 *
 * @author Deniss Larka
 * on 06 April 2020
 */

public record OptionPriceRequest(OptionSide side,
								 double spotPrice,
								 double strike,
								 double yearsToMaturity,
								 double riskFreeInterest,
								 double volatility) {
	public boolean isCall() {
		return side == OptionSide.CALL;
	}
}


