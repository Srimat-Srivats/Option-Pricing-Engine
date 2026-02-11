# Monte Carlo Option Pricing Engine

A Java-based Monte Carlo pricing engine for European and arithmetic-average Asian options under Geometric Brownian Motion.

## Features

- European call and put options
- Arithmetic Asian options
- Antithetic variates for variance reduction
- 95% confidence intervals
- CLI-based execution
- Dockerized deployment

## Model

The underlying asset follows Geometric Brownian Motion:

dS = rS dt + σS dW

## Build

```bash
javac --release 17 -d out src/main/java/com/quant/engine/*.java
jar cfm option-pricing-engine.jar manifest.txt -C out .
