package hr.fer.zemris.lab6.demo;

import hr.fer.zemris.lab6.neuro_fuzzy.ANFIS;

public class Demo {

	public static void main(String[] args) {
		
		ANFIS neuroFuzzy = new ANFIS(0.005, 100000, 6);
		neuroFuzzy.stohastic();
		
	}
	
}
