package com.druvu.options.math.price;

/**
 * @author Deniss Larka
 * on 20 April 2020
 */
public final class OptionPriceMath {

	// The Abramowitz & Stegun (1964) numerical approximation
	// below uses six constant values in its formula.
	private static final double P = 0.2316419;
	private static final double B1 = 0.319381530;
	private static final double B2 = -0.356563782;
	private static final double B3 = 1.781477937;
	private static final double B4 = -1.821255978;
	private static final double B5 = 1.330274429;

	private OptionPriceMath() {
	}

	public static double price(OptionPriceRequest request) {
		final double d1 = d1(request.spotPrice(), request.strike(), request.riskFreeInterest(), request.yearsToMaturity(), request.volatility());
		final double d2 = d2(d1, request.yearsToMaturity(), request.volatility());
		if (request.isCall()) {
			double cd1 = cumulDistrNorm(d1);
			double cd2 = cumulDistrNorm(d2);
			return request.spotPrice() * cd1 - request.strike() * Math.exp(-request.riskFreeInterest() * request.yearsToMaturity()) * cd2;
		} else {
			double cd1 = cumulDistrNorm(-d1);
			double cd2 = cumulDistrNorm(-d2);
			return request.strike() * Math.exp(-request.riskFreeInterest() * request.yearsToMaturity()) * cd2 - request.spotPrice() * cd1;
		}
	}

	public static OptionGreeks greeks(OptionPriceRequest req) {
		double d1 = d1(req.spotPrice(), req.strike(), req.riskFreeInterest(), req.yearsToMaturity(), req.volatility());
		double d2 = d2(d1, req.yearsToMaturity(), req.volatility());
		double sd1 = stdNormDistr(d1);
		double cd1 = cumulDistr(d1, sd1);
		double thetaLeft = -(req.strike() * sd1 * req.volatility()) / (2 * Math.sqrt(req.yearsToMaturity()));
		double gamma = sd1 / (req.spotPrice() * req.volatility() * Math.sqrt(req.yearsToMaturity()));
		double vega = req.spotPrice() * sd1 * Math.sqrt(req.yearsToMaturity());

		if (req.isCall()) {
			double cd2 = cumulDistrNorm(d2);
			double price = req.spotPrice() * cd1 - req.strike() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * cd2;
			double delta = cd1;
			double thetaRight = req.riskFreeInterest() * req.strike() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * cd2;
			double theta = thetaLeft - thetaRight;
			double rho = req.strike() * req.yearsToMaturity() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * cd2;
			return new OptionGreeks(price, delta, gamma, vega, theta, rho);
		} else {
			double pcd1 = cumulDistrNorm(-d1);
			double pcd2 = cumulDistrNorm(-d2);
			double price = req.strike() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * pcd2 - req.spotPrice() * pcd1;
			double delta = cd1 - 1;
			double thetaRight = req.riskFreeInterest() * req.strike() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * pcd2;
			double theta = thetaLeft + thetaRight;
			double rho = -req.strike() * req.yearsToMaturity() * Math.exp(-req.riskFreeInterest() * req.yearsToMaturity()) * pcd2;
			return new OptionGreeks(price, delta, gamma, vega, theta, rho);
		}
	}

	/**
	 * @param spotPrice        = Spot price of underlying stock/asset
	 * @param strike           = Strike price
	 * @param riskFreeInterest = Risk free annual interest rate continuously compounded
	 * @param maturityInYears  = Time in years until option expiration (maturity)
	 * @param volatility       = Implied volatility of returns of underlying stock/asset
	 */
	private static double d1(double spotPrice, double strike, double riskFreeInterest, double maturityInYears, double volatility) {
		double top = Math.log(spotPrice / strike) + (riskFreeInterest + Math.pow(volatility, 2) / 2) * maturityInYears;
		double bottom = volatility * Math.sqrt(maturityInYears);
		return top / bottom;
	}

	/**
	 * @param d1              = already calculated d1
	 * @param maturityInYears = Time in years until option expiration (maturity)
	 * @param volatility      = Implied volatility of returns of underlying stock/asset
	 */
	private static double d2(double d1, double maturityInYears, double volatility) {
		return d1 - volatility * Math.sqrt(maturityInYears);
	}

	private static double cumulDistrNorm(double x) {
		return cumulDistr(x, stdNormDistr(x));
	}

	private static double cumulDistr(double x, double sdx) {
		double t = 1 / (1 + P * Math.abs(x));
		double t1 = B1 * t;
		double t2 = B2 * Math.pow(t, 2);
		double t3 = B3 * Math.pow(t, 3);
		double t4 = B4 * Math.pow(t, 4);
		double t5 = B5 * Math.pow(t, 5);
		double b = t1 + t2 + t3 + t4 + t5;
		double cd = 1 - sdx * b;
		return x < 0 ? 1 - cd : cd;
	}

	private static double stdNormDistr(double x) {
		double top = Math.exp(-0.5 * Math.pow(x, 2));
		double bottom = Math.sqrt(2 * Math.PI);
		return top / bottom;
	}

}
