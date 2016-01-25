package hr.fer.zemris.lab7.demo;

import java.io.IOException;

import hr.fer.zemris.lab7.neuro_evolution.Dataset;
import hr.fer.zemris.lab7.neuro_evolution.Genetic;
import hr.fer.zemris.lab7.neuro_evolution.NeuralNetwork;

/**
 * Class which demonstrates the workings of our neural net. 
 * 
 * @author ipaljak
 *
 */

public class Demo {

	public static void main(String[] args) throws IOException {
		
		Dataset data = new Dataset("zad7-dataset.txt");
		NeuralNetwork net = new NeuralNetwork(2,8,4,3);
		Genetic genAlg = new Genetic(net, data);
		
		genAlg.run();
		
		net.classify(data, genAlg.getOptimalParams());
		
	}
	
}
