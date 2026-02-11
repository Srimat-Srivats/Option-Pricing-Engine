# Monte Carlo Option Pricing Engine (Java)

A production-style Monte Carlo pricing engine for European and arithmetic-average Asian options under the Geometric Brownian Motion (GBM) model.  
The engine supports variance reduction via antithetic variates and reports statistical confidence intervals.

This repository is intended as a quantitative finance and numerical methods portfolio project.

---

## Overview

This project implements a modular Monte Carlo simulation framework for pricing equity derivatives under the risk-neutral measure.  
It supports both terminal-payoff and path-dependent contracts using a unified simulation engine and a clean payoff abstraction.

The application is provided as a command-line tool and is packaged as an executable JAR and a Docker container.

---

## Model

The underlying asset price follows a Geometric Brownian Motion:

dS(t) = r S(t) dt + σ S(t) dW(t)

The exact discretization used in the simulation is:

S(t + Δt) = S(t) · exp[(r − 0.5 σ²) Δt + σ √Δt Z]

where Z ~ N(0, 1).

All pricing is performed under the risk-neutral measure and discounted using the continuously compounded risk-free rate.

---

## Implemented Contracts

### European Options

Call payoff:

max(S(T) − K, 0)

Put payoff:

max(K − S(T), 0)

---

### Arithmetic-Average Asian Options

Let

A = (1 / N) Σ S(t_i)

Call payoff:

max(A − K, 0)

Put payoff:

max(K − A, 0)

These options are path-dependent and do not admit a closed-form solution under the GBM model, making Monte Carlo simulation the standard pricing approach.

---

## Numerical Techniques

### Monte Carlo Simulation

A large number of independent asset price paths are generated using the exact GBM discretization.  
For each path, the option payoff is evaluated and the discounted average of all payoffs is used as the price estimator.

---

### Antithetic Variates

For each standard normal draw Z, the paired draw −Z is also simulated.  
This introduces negative correlation between paired paths and reduces the variance of the estimator without introducing bias.

---

### Statistical Confidence Interval

The standard error of the Monte Carlo estimator is computed and a 95% confidence interval is reported:

price ± 1.96 × standard error

---

## Features

- European call and put options
- Arithmetic-average Asian options
- Antithetic variates for variance reduction
- 95% confidence intervals
- Reproducible simulation using a fixed random seed
- Modular payoff interface
- Command-line interface
- Executable JAR packaging
- Dockerized runtime

---

## Build

Java 17 compatible build:

```bash

✔ Build
javac --release 17 -d out src/main/java/com/quant/engine/*.java

jar cfm option-pricing-engine.jar manifest.txt -C out .

✔ Run
java -jar option-pricing-engine.jar 100 100 0.05 0.2 1 252 200000 CALL EURO

✔ Docker
docker build -t option-pricing-engine .

docker run option-pricing-engine
