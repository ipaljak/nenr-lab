package hr.fer.zemris.lab4.evolutionary;

import java.util.Random;

public class Selection extends Genetic {

	public Selection(Measurements m) {
		super(m);
	}
	
	
	@Override
	protected void next_generation() {
		
		Random r = GeneticConstants.r;
		
		int a = r.nextInt(GeneticConstants.POPULATION);
		
		int b = a;
		while (a == b)
			b = r.nextInt(GeneticConstants.POPULATION);
		
		int c = b;
		while (c == a || c == b)
			c = r.nextInt(GeneticConstants.POPULATION);
		
		int pivot = worst(a, b, c);
		if (a == pivot) {
			int tmp = a; a = c; c = tmp;
		}
		if (b == pivot) {
			int tmp = b; b = c; c = tmp; 
		}
		
		this.population.set(pivot, Chromosome.crossover(this.population.get(a), this.population.get(b)));
		
	}
	
	private int worst(int a, int b, int c) {
		if (a > b && a > c) return a;
		if (b > a && b > c) return b;
		return c;
	}
	
}
