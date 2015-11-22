package hr.fer.zemris.lab4.evolutionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation extends Genetic {

	private boolean use_elitism;
	
	public Generation(Measurements m, boolean use_elitism) {
		super(m);
		this.use_elitism = use_elitism;
	}
	
	private Chromosome getParent() {
		Random r = GeneticConstants.r;
		
		double errorSum = 0, maxError = 0;
		for (int i = 0; i < this.population.size(); ++i) {
			errorSum += this.population.get(i).getError();
			maxError = Math.max(maxError, this.population.get(i).getError());
		}
		
		double cutTresh = r.nextDouble() * (GeneticConstants.POPULATION * maxError - errorSum);
		
		errorSum = 0;
		for (int i = 0; i < this.population.size(); ++i) {
			errorSum += maxError - this.population.get(i).getError();
			if (errorSum > cutTresh) {
				return this.population.get(i);
			}
		}
		
		return this.population.get(this.population.size() - 1);
	}
	
	@Override
	protected void next_generation() {
		
		List<Chromosome> new_population = new ArrayList<Chromosome>();
		for (int i = 0; i < GeneticConstants.POPULATION; ++i) {
			Chromosome c1 = getParent();
			Chromosome c2 = getParent();
			new_population.add(Chromosome.crossover(c1, c2));
			new_population.get(i).mutate();
		}
		
		if (use_elitism) 
			new_population.set(0, this.population.get(0));
		
		this.population = new_population;
		
	}
	
}
