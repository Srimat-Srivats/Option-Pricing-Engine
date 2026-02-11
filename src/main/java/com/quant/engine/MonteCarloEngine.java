package com.quant.engine;

import java.util.Random;

public class MonteCarloEngine {

    public static SimulationResult price(
            double s0,
            double r,
            double sigma,
            double t,
            int steps,
            int paths,
            Payoff payoff,
            long seed
    ) {

        Random rnd = new Random(seed);

        double dt = t / steps;
        double drift = (r - 0.5 * sigma * sigma) * dt;
        double vol = sigma * Math.sqrt(dt);

        double discount = Math.exp(-r * t);

        double sum = 0.0;
        double sumSq = 0.0;

        int effectivePaths = 0;

        for (int i = 0; i < paths / 2; i++) {

            double[] path1 = new double[steps];
            double[] path2 = new double[steps];

            double s1 = s0;
            double s2 = s0;

            for (int j = 0; j < steps; j++) {

                double z = rnd.nextGaussian();

                s1 = s1 * Math.exp(drift + vol * z);
                s2 = s2 * Math.exp(drift - vol * z);

                path1[j] = s1;
                path2[j] = s2;
            }

            double p1 = payoff.payoff(path1);
            double p2 = payoff.payoff(path2);

            sum += p1 + p2;
            sumSq += p1 * p1 + p2 * p2;

            effectivePaths += 2;
        }

        double mean = sum / effectivePaths;
        double var = (sumSq / effectivePaths) - mean * mean;

        double price = discount * mean;
        double stdError = discount * Math.sqrt(var / effectivePaths);

        double ciLow = price - 1.96 * stdError;
        double ciHigh = price + 1.96 * stdError;

        return new SimulationResult(price, stdError, ciLow, ciHigh);
    }
}
