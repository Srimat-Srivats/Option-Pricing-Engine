package com.quant.engine;

public class SimulationResult {

    public final double price;
    public final double stdError;
    public final double ciLow;
    public final double ciHigh;

    public SimulationResult(double price, double stdError,
                            double ciLow, double ciHigh) {
        this.price = price;
        this.stdError = stdError;
        this.ciLow = ciLow;
        this.ciHigh = ciHigh;
    }
}
