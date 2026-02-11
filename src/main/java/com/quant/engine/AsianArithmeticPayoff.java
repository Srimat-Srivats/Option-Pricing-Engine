package com.quant.engine;

public class AsianArithmeticPayoff implements Payoff {

    private final double strike;
    private final OptionType type;

    public AsianArithmeticPayoff(double strike, OptionType type) {
        this.strike = strike;
        this.type = type;
    }

    @Override
    public double payoff(double[] path) {

        double sum = 0.0;
        for (double v : path) sum += v;

        double avg = sum / path.length;

        if (type == OptionType.CALL)
            return Math.max(avg - strike, 0.0);
        else
            return Math.max(strike - avg, 0.0);
    }
}
