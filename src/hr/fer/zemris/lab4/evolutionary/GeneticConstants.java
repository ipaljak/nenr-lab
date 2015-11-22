package hr.fer.zemris.lab4.evolutionary;

import java.util.Random;

/**
 * Collections of predefined constants used for genetic algorithm.
 * 
 * @author ipaljak
 *
 */

public class GeneticConstants {

	public static final int POPULATION = 100;
	public static final int GENERATIONS = 2000;
	
	public static final double P_MUTATION = 0.2;
	public static final double MIN_ERROR = 1e-6;

	public static final Random r = new Random();
	
}
