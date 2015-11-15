package hr.fer.zemris.lab3.demo;

import java.util.Scanner;

import hr.fer.zemris.lab3.control.COADefuzzifier;
import hr.fer.zemris.lab3.control.Defuzzifier;
import hr.fer.zemris.lab3.control.FuzzySystem;
import hr.fer.zemris.lab3.control.HelmFuzzySystem;

public class Run {

	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(System.in);
		
		Defuzzifier def = new COADefuzzifier();
		
		FuzzySystem HelmFS = new HelmFuzzySystem(def);
		
		while (true) {
			int vals[] = new int[6];
			for (int i = 0; i < 6; ++i)
				vals[i] = in.nextInt();
			double K = HelmFS.deduce(vals);
			System.out.printf("0 %d\n", (int) K);
			System.out.flush();
		}
		
	}
	
}
