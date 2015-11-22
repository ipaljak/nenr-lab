package hr.fer.zemris.lab4.evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstraction of a genetic algorithm
 * 
 * @author ipaljak
 *
 */

public abstract class Genetic {

	protected List<Chromosome> population;
	protected Measurements m;
	
	public Genetic(Measurements m) {
		this.m = m;
		population = new ArrayList<Chromosome>();
		for (int i = 0; i < GeneticConstants.POPULATION; ++i) {
			population.add(new Chromosome(m));
			population.get(i).generate_random();
		}
	}
	
	public Chromosome run() {
		for (int i = 0; i < GeneticConstants.GENERATIONS; ++i) {
			
			Collections.sort(population);
			double error = population.get(0).getError();
		
			System.out.println("Generation #" + i + ", error = " + error);
			
			if (error < GeneticConstants.MIN_ERROR) break;
			next_generation();
		
		}
		Collections.sort(population);
		return population.get(0);
	}
	
	protected abstract void next_generation();
	
}
