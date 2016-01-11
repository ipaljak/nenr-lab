package hr.fer.zemris.lab5.demo;

import hr.fer.zemris.lab5.backend.Backpropagation;
import hr.fer.zemris.lab5.backend.NeuralNet;

public class debilana {

	public static void main(String[] args) throws Exception {
		
		NeuralNet zamre = new Backpropagation("examples.txt", 10000, 40, 5, 4, 0.01);
		zamre.learn();
		
	}
	
}
