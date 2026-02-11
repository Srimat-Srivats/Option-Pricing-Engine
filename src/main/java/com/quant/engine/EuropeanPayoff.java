package com.quant.engine;

public class EuropeanPayoff implements Payoff {

    private final double strike;
    private final OptionType type;

    public EuropeanPayoff(double strike, OptionType type) {
        this.strike = strike;
        this.type = type;
    }

    @Override
    public double payoff(double[] path) {
        double st = path[path.length - 1];
        if (type == OptionType.CALL)
            return Math.max(st - strike, 0.0);
        else
            return Math.max(strike - st, 0.0);
    }
}
