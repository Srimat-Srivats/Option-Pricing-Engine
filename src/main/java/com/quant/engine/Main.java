package com.quant.engine;

public class Main {

    public static void main(String[] args) {

        if (args.length < 9) {
            System.out.println("Usage:");
            System.out.println("S0 K r sigma T steps paths CALL/PUT EURO/ASIAN");
            return;
        }

        double s0 = Double.parseDouble(args[0]);
        double k = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);
        double sigma = Double.parseDouble(args[3]);
        double t = Double.parseDouble(args[4]);
        int steps = Integer.parseInt(args[5]);
        int paths = Integer.parseInt(args[6]);
        OptionType type = OptionType.valueOf(args[7]);
        String style = args[8];

        Payoff payoff;

        if (style.equalsIgnoreCase("EURO")) {
            payoff = new EuropeanPayoff(k, type);
        } else {
            payoff = new AsianArithmeticPayoff(k, type);
        }

        SimulationResult res = MonteCarloEngine.price(
                s0, r, sigma, t, steps, paths, payoff, 42L
        );

        System.out.println("Price        : " + res.price);
        System.out.println("Std Error    : " + res.stdError);
        System.out.println("95% CI       : [" + res.ciLow + ", " + res.ciHigh + "]");
    }
}
