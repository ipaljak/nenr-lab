package hr.fer.zemris.lab4.evolutionary;

import java.util.Random;

/**
 * Class which models a single chromosome unit for a genetic algorithm.
 * 
 * @author ipaljak
 *
 */

public class Chromosome implements Comparable<Chromosome>{

	private double[] beta;
	private double error;
	
	private Measurements m;
	
	public Chromosome(Measurements m) {
		beta = new double[5];
		this.m = m;
	}
	
	public Chromosome(double[] beta, Measurements m) {
		this.beta = beta;
		this.m = m;
		this.error = m.getError(m.evaluateAll(this));
	}

	public double[] getBeta() {
		return beta;
	}

	public void setBeta(double[] beta) {
		this.beta = beta;
		this.error = m.getError(m.evaluateAll(this));
	}

	public void generate_random() {
		Random r = GeneticConstants.r;
		for (int i = 0; i < 5; ++i) 
			beta[i] = -4 + 8 * r.nextDouble();
		error = m.getError(m.evaluateAll(this));
	}

	public static Chromosome crossover(Chromosome c1, Chromosome c2) {
		double[] newBeta = new double[5];
		for (int i = 0; i < 5; ++i) 
			newBeta[i] = (c1.getBeta()[i] + c2.getBeta()[i]) / 2;
		return new Chromosome(newBeta, c1.m);
	}
	
	public void mutate() {
		Random r = GeneticConstants.r;
		for (int i = 0; i < 5; ++i) 
			if (r.nextDouble() < GeneticConstants.P_MUTATION)
				beta[i] = -4 + 8 * r.nextDouble();
		this.error = m.getError(m.evaluateAll(this));
	}
	
	public double getError() {
		return error;
	}
		
	@Override
	public int compareTo(Chromosome o) {
		double e1 = this.getError(), e2 = o.getError();
		if (e1 < e2) return -1;
		if (e1 > e2) return 1;
		return 0;
	}
	
}
