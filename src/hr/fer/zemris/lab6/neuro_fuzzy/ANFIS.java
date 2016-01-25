package hr.fer.zemris.lab6.neuro_fuzzy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Neuro-fuzzy system which models a two-variable function using the Takagi-Sugeno-Kang method. 
 * 
 * @author ipaljak
 *
 */

public class ANFIS {

	private double learningRate, maxIter, sumW, sumWZ; 
	
	List<Rule> rules;
	List<Sample> samples;
	
	public ANFIS(double learningRate, double maxIter, int ruleCnt) {
		
		this.learningRate = learningRate; 
		this.maxIter = maxIter;
	
		rules = new ArrayList<Rule>();
		for (int i = 0; i < ruleCnt; ++i)
			rules.add(new Rule());
		
		samples = new ArrayList<Sample>();
		for (int x = -4; x < 4; ++x)
			for (int y = -4; y < 4; ++y)
				samples.add(new Sample(x, y));
		
	}

	public List<Rule> getRules() {
		return rules;
	}
	
	private double evaluate(double x, double y) {
		sumW = 0;
		sumWZ = 0;
		for(Rule r : rules) {
			double w = r.getW(x, y);
			sumW += w;
			sumWZ += w * r.getF(x, y);
		}
		return sumWZ / sumW;
	}
	
	public List<Double> getErrors() {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0; i < samples.size(); ++i) 
			ret.add(Math.abs(samples.get(i).z - evaluate(samples.get(i).x, samples.get(i).y)));
		return ret;
	}	

	private double MSE() {
		double ret = 0;
		for (Sample s : samples)
			ret += Math.pow(evaluate(s.x, s.y) - s.z, 2);
		return ret / samples.size();
	}
	 
	public void batch() {
		
		for (int iter = 0; iter < maxIter; ++iter) {
			
			for(Sample s : samples) {
				double o = evaluate(s.x, s.y);
				for(Rule r : rules) {
					double z = r.getF(s.x, s.y);
					r.updateDerivatives(s, o, sumW, sumW * z - sumWZ);
				}
			}

			for(Rule r : rules) 
				r.update(learningRate);
			
			if (iter % 500 == 0)
				System.out.println("Iteration #"+iter + ": MSE = " + MSE());
		
		}
		
	}
	
	public void stohastic() {
		
		for (int iter = 0; iter < maxIter; ++iter) {
			
			if (iter % samples.size() == 0)
				Collections.shuffle(samples);
			
			Sample currSample = samples.get(iter % samples.size());
			double o = evaluate(currSample.x, currSample.y);
			
			for (Rule r : rules) {
				double z = r.getF(currSample.x, currSample.y);
				r.updateDerivatives(currSample, o, sumW, sumW * z - sumWZ);
				r.update(learningRate);
			}
			
			if (iter % 500 == 0)
				System.out.println("Iteration #"+iter + ": MSE = " + MSE());
		
		}
		
	}
	
}
