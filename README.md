# Monte Carlo Option Pricing Engine (Java)

A production-style Monte Carlo pricing engine for European and arithmetic-average Asian options under the Geometric Brownian Motion (GBM) model.  
The engine supports variance reduction via antithetic variates and reports statistical confidence intervals.

This project is designed as a quantitative finance and numerical methods portfolio project.

---

## Overview

This project implements a modular Monte Carlo simulation framework to price equity derivatives under the risk-neutral measure.  
It supports both terminal-payoff and path-dependent contracts using a unified simulation engine.

The application is provided as a command-line tool and is fully containerized using Docker.

---

## Model

The underlying asset follows a Geometric Brownian Motion:

dS(t) = r S(t) dt + σ S(t) dW(t)

with the exact discretization:

S(t+Δt) = S(t) · exp[(r − 0.5 σ²) Δt + σ √Δt Z]

where Z ~ N(0, 1).

All pricing is performed under the risk-neutral measure and discounted using the continuously compounded risk-free rate.

---

## Implemented Contracts

### European Options

Call payoff  
max(S(T) − K, 0)

Put payoff  
max(K − S(T), 0)

---

### Arithmetic-Average Asian Options

Let

A = (1 / N) Σ S(t_i)

Call payoff  
max(A − K, 0)

Put payoff  
max(K − A, 0)

This option does not admit a closed-form solution under GBM and is therefore priced using Monte Carlo simulation.

---

## Numerical Techniques

### Monte Carlo Simulation

A large number of independent price paths are generated using exact GBM discretization.  
The discounted average of the simulated payoffs is used as the estimator of the option value.

---

### Antithetic Variates

For each standard normal draw Z, the paired draw −Z is also simulated.  
This introduces negative correlation between paired paths and reduces estimator variance without introducing bias.

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
- Reproducible simulation via fixed random seed
- Modular payoff interface
- Command-line interface
- Executable JAR packaging
- Dockerized runtime

---

## Project Structure
