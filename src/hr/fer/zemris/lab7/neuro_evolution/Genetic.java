package hr.fer.zemris.lab7.neuro_evolution;

import java.util.Random;

/**
 * Implementation of a genetic algorithm used to find optimal network parameters. 
 * 
 * @author ipaljak
 *
 */

public class Genetic {
	
	private int bestId; 
	
	private double bestError;
	
	private double[] errors; 
	private double[][] generation; 

	private NeuralNetwork neuralNet; 
	private Random rand; 
	
	private Dataset data;
	
	public Genetic(NeuralNetwork neuralNet, Dataset data) {
		
		this.data = data;
		
		this.neuralNet = neuralNet;
		this.rand = new Random();
		
		this.errors = new double[GeneticConstants.GENERATION_SIZE];
		this.generation = new double[GeneticConstants.GENERATION_SIZE][neuralNet.getParameterSize()];
		
		for (int i = 0; i < GeneticConstants.GENERATION_SIZE; ++i)
			for (int j = 0; j < generation[i].length; ++j) 
				generation[i][j] = rand.nextGaussian() * GeneticConstants.SIGM2;
		
		findBestChromosome();
		
	}
	
	private void findBestChromosome() {
		
		bestId = 0;
		bestError = errors[0] = neuralNet.calcError(data, generation[0]);
		
		for (int i = 1; i < generation.length; ++i) {
			neuralNet.calcOutput(generation[i]);
			errors[i] = neuralNet.calcError(data, generation[i]);
			if (errors[i] < bestError) {
				bestId = i;
				bestError = errors[i];  
			}
		}
		
	}
	
	private double[] cross1(double[] p1, double[] p2) {
		double[] child = new double[p1.length];
		for (int i = 0; i < p1.length; ++i)
			child[i] = (p1[i] + p2[1]) / 2;
		return child;
	}

	private double[] cross2(double[] p1, double[] p2) {
		double[] child = new double[p1.length];
		for (int i = 0; i < p1.length; ++i) 
			if (rand.nextBoolean()) 
				child[i] = p1[i];
			else
				child[i] = p2[i];
		return child;
	}
	
	private double[] cross3(double[] p1, double[] p2) {
		double[] child = new double[p1.length];
		int pref = rand.nextInt() % p1.length;
		for (int i = 0; i < p1.length; ++i) 
			if (i <= pref) 
				child[i] = p1[i];
			else
				child[i] = p2[i];
		return child;
	}
	
	private void mutate1(int id) {
		for (int i = 0; i < generation[id].length; ++i) 
			if (rand.nextDouble() < 0.01) generation[id][i] += rand.nextGaussian() * GeneticConstants.SIGM1;
	}
	
	private void mutate2(int id) {
		for (int i = 0; i < generation[id].length; ++i) 
			if (rand.nextDouble() < 0.01) generation[id][i] = rand.nextGaussian() * GeneticConstants.SIGM2;	
	}
	
	public void run() {
		
		int iter = 0;
		while (iter < GeneticConstants.MAX_ITER * GeneticConstants.GENERATION_SIZE && bestError > 1e-7) {
			
			int ch1 = Math.abs(rand.nextInt()) % GeneticConstants.GENERATION_SIZE, ch2, ch3;
			while ((ch2 = Math.abs(rand.nextInt()) % GeneticConstants.GENERATION_SIZE) == ch1);
			while ((ch3 = Math.abs(rand.nextInt()) % GeneticConstants.GENERATION_SIZE) == ch1 || ch3 == ch2);
			
			if (errors[ch2] > errors[ch3]) {
				ch2 = ch2 ^ ch3;
				ch3 = ch2 ^ ch3;
				ch2 = ch2 ^ ch3;
			}
			
			if (errors[ch1] > errors[ch3]) {
				ch1 = ch1 ^ ch3;
				ch3 = ch1 ^ ch3;
				ch1 = ch1 ^ ch3;
			}
			
			int die = rand.nextInt() % 3;
			
			if (die == 0) generation[ch3] = cross1(generation[ch1], generation[ch2]);
			if (die == 1) generation[ch3] = cross2(generation[ch1], generation[ch2]);
			if (die == 2) generation[ch3] = cross3(generation[ch1], generation[ch2]);
			
			if (rand.nextDouble() < GeneticConstants.P)
				mutate1(ch3);
			else 
				mutate2(ch3);
			
			errors[ch3] = neuralNet.calcError(data, generation[ch3]);
			if (errors[ch3] < bestError) {
				bestError = errors[ch3];
				bestId = ch3; 
			}
			
			++iter; 
			
			System.out.println("Best chromosome at iteration #" + iter + " has error " + bestError);
			
		}
		
	}
	
	public double[] getOptimalParams() {
		return generation[bestId];
	}
	
}
