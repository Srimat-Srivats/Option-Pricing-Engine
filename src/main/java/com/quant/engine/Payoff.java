package com.quant.engine;

public interface Payoff {
    double payoff(double[] path);
}
