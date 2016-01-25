package hr.fer.zemris.lab7.neuro_evolution;

import hr.fer.zemris.lab7.neuro_evolution.Dataset.Sample;

/**
 * An implementation of a layered neural network. 
 * 
 * @author ipaljak
 *
 */

public class NeuralNetwork {
	
	private int[] layerSizes;
	
	private double[] neurons; 
	
	public NeuralNetwork(int... layerSizes) {
		
		int neuronCnt = 0;
		for (int size : layerSizes)
			neuronCnt += size;
		
		this.layerSizes = layerSizes;
		this.neurons = new double[neuronCnt];
		
	}

	public int getParameterSize() {
		int cnt = layerSizes[0] * layerSizes[1] * 2;
		for (int i = 2; i < layerSizes.length; ++i)
			cnt += layerSizes[i] * (layerSizes[i - 1] + 1);
		return cnt; 
	}
	
	double f1(double net) {
		return 1 / (1 + net);
	}
	
	double f2(double net) {
		return 1 / (1 + Math.exp(-net));
	}
	
	public void setInput(double x, double y) {
		neurons[0] = x;
		neurons[1] = y;
	}
	
	public void calcOutput(double[] params) {
		
		int prevStart = 0, prevEnd = 2; // Previous layer outputs are in interval [prevStart, prevEnd>
		int paramId = 0; // Current parameter index
		
		for (int i = 1; i < layerSizes.length; ++i) {
			
			for (int j = 0; j < layerSizes[i]; ++j) {
			
				double sum = 0;
				for (int k = prevStart; k < prevEnd; ++k) {
					double x = neurons[k];
					if (i == 1) {
						double w = params[paramId++]; 
						double s = params[paramId++];
						sum += Math.abs(x - w) / Math.abs(s);
					} else {
						double w = params[paramId++];
						sum += x * w;
					}
				}
				
				if (i == 1) 
					neurons[prevEnd + j] = f1(sum);			
				else
					neurons[prevEnd + j] = f2(sum);
			}
			
			prevStart = prevEnd;
			prevEnd += layerSizes[i];
			
		}
		
	}
	
	public double calcError(Dataset data, double[] params) {
	
		double error = 0;
		int N = neurons.length;
		
		for (Sample s : data.getSamples()) {
			this.setInput(s.x, s.y);
			this.calcOutput(params);
			error += Math.pow(neurons[N - 3] - s.o1, 2);
			error += Math.pow(neurons[N - 2] - s.o2, 2);
			error += Math.pow(neurons[N - 1] - s.o3, 2);
		}
		
		return error / data.getSize();

	}
	
	public void classify(Dataset data, double[] params) {
		
		int ac = 0, wa = 0;
		int N = neurons.length;
		for (Sample s : data.getSamples()) {
			
			this.setInput(s.x, s.y);
			this.calcOutput(params);
			
			if (neurons[N - 3] < 0.5) neurons[N - 3] = 0; else neurons[N - 3] = 1;
			if (neurons[N - 2] < 0.5) neurons[N - 2] = 0; else neurons[N - 2] = 1;
			if (neurons[N - 1] < 0.5) neurons[N - 1] = 0; else neurons[N - 1] = 1;
			
			if (neurons[N - 3] == s.o1 && neurons[N - 2] == s.o2 && neurons[N - 1] == s.o3)
				++ac;
			else
				++wa;
			
		}
		
		System.out.println("Correct: " + ac);
		System.out.println("Wrong:" + wa);
		
	}
	
} 	
