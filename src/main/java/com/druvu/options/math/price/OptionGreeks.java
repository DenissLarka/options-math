package com.druvu.options.math.price;

/**
 * Hold option "Greeks"
 * @see <a href="https://wikipedia.org/wiki/Greeks_(finance)">https://wikipedia.org/wiki/Greeks_(finance)</a>
 *
 * @author : Deniss Larka
 * on 10 July 2021
 **/

public record OptionGreeks(double price, double delta, double gamma, double vega, double theta, double rho) {
}
