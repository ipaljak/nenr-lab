package hr.fer.zemris.lab4.demo;

import java.io.IOException;

import hr.fer.zemris.lab4.evolutionary.Chromosome;
import hr.fer.zemris.lab4.evolutionary.Generation;
import hr.fer.zemris.lab4.evolutionary.Genetic;
import hr.fer.zemris.lab4.evolutionary.Measurements;
import hr.fer.zemris.lab4.evolutionary.Selection;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Measurements m = new Measurements("zad4-dataset1.txt");
		Genetic g = new Selection(m);
		
		Chromosome c = g.run();
		
		for (int i = 0; i < 5; ++i) 
			System.out.print(c.getBeta()[i] + " ");
	
		
		
	}
	
}
