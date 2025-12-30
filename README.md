# options-math

[![Maven Central](https://img.shields.io/maven-central/v/com.druvu/options-math.svg)](https://central.sonatype.com/artifact/com.druvu/options-math)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DenissLarka_options-math&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=DenissLarka_options-math)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=DenissLarka_options-math&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=DenissLarka_options-math)

Java library to calculate European option prices and Greeks using the Black-Scholes pricing model.

## Installation

### Maven
```xml
<dependency>
    <groupId>com.druvu</groupId>
    <artifactId>options-math</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle
```groovy
implementation 'com.druvu:options-math:1.0.1'
```

## Usage

### Calculate Option Price

```java
import com.druvu.options.math.OptionSide;
import com.druvu.options.math.price.OptionPriceMath;
import com.druvu.options.math.price.OptionPriceRequest;

// Parameters: side, spotPrice, strike, yearsToMaturity, riskFreeInterest, volatility
OptionPriceRequest callRequest = new OptionPriceRequest(
    OptionSide.CALL,
    300,    // spot price
    250,    // strike
    1,      // 1 year to maturity
    0.05,   // 5% risk-free rate
    0.15    // 15% volatility
);

double callPrice = OptionPriceMath.price(callRequest);  // 63.24

OptionPriceRequest putRequest = new OptionPriceRequest(OptionSide.PUT, 300, 250, 1, 0.05, 0.15);
double putPrice = OptionPriceMath.price(putRequest);    // 1.05
```

### Calculate Greeks

```java
import com.druvu.options.math.price.OptionGreeks;

OptionGreeks greeks = OptionPriceMath.greeks(callRequest);

greeks.price();  // 63.24 - theoretical option price
greeks.delta();  // 0.948 - rate of change of price with respect to underlying
greeks.gamma();  // 0.002 - rate of change of delta
greeks.vega();   // sensitivity to volatility
greeks.theta();  // time decay
greeks.rho();    // 221.10 - sensitivity to interest rate
```

### Calculate Profit/Loss

```java
import com.druvu.options.math.profit.MarketSide;
import com.druvu.options.math.profit.OptionProfitMath;
import com.druvu.options.math.profit.OptionProfitRequest;

// Bought 10 call options at strike 250, paid $5 premium each
OptionProfitRequest profitRequest = new OptionProfitRequest(
    OptionSide.CALL,
    MarketSide.BUY,
    250,    // strike
    5,      // premium paid
    10      // number of options
);

double profit = OptionProfitMath.profit(profitRequest, 280);  // spot at expiry = 280
// Profit = (280 - 250 - 5) * 10 = 250
```

## License

Apache License 2.0
