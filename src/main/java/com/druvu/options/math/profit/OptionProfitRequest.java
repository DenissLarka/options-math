package com.druvu.options.math.profit;

import com.druvu.options.math.OptionSide;

/**
 * An option profit calculation request with all required parameters
 *
 * @param marketSide      - given market side
 * @param optionSide      - option side
 * @param strike          - strike price
 * @param premium         - premium
 * @param numberOfOptions - number of options
 *
 * @author Deniss Larka
 * on 23 April 2020
 */
public record OptionProfitRequest(MarketSide marketSide,
								  OptionSide optionSide,
								  double strike,
								  double premium,
								  int numberOfOptions) {

	public boolean isBuy() {
		return marketSide == MarketSide.BUY;
	}

	public boolean isCall() {
		return optionSide == OptionSide.CALL;
	}
}
