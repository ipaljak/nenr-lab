package hr.fer.zemris.lab6.demo;

import hr.fer.zemris.lab6.neuro_fuzzy.ANFIS;
import hr.fer.zemris.lab6.neuro_fuzzy.Rule;

public class Demo {

	public static void main(String[] args) {
		
		ANFIS neuroFuzzy = new ANFIS(0.001, 100000, 7);
		neuroFuzzy.stohastic();

		
	}
	
}
