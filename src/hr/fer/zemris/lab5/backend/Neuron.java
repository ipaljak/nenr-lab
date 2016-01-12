package hr.fer.zemris.lab5.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class models a single neuron. 
 * 
 * @author ipaljak
 *
 */

public class Neuron {

	private static Random generator = new Random();
	
	private boolean inInputLayer;
	private double y, w0, delta;
	private int id; 
	
	private List<Double> weights;
	private List<Double> tempWeights;
	
	public Neuron(boolean inInputLayer, int nextLayerSize, int id) {
		this.inInputLayer = inInputLayer;
		this.weights = new ArrayList<Double>();
		this.id = id;
		delta = 0;
		setWeights(nextLayerSize);
	}

	private void setWeights(int size) {
		this.w0 = generator.nextDouble() * 4 - 2.0;
		for (int i = 0; i < size; ++i) {
			this.weights.add(generator.nextDouble() * 4 - 2.0);
		}
		this.tempWeights = new ArrayList<Double>(this.weights);
	}
	
	public void setW0(double w0) {
		this.w0 = w0;
	}
	
	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	public double getDelta() {
		return this.delta;
	}
	
	public void setDelta(Layer next) {
		this.delta = 0;
		for (int i = 0; i < next.size(); ++i) {
			this.delta += this.getY() * (1 - this.getY()) * this.weights.get(i) * next.getNeuron(i).getDelta();
		}
	}
	
	public void updateWeights(Layer next, double learningRate) {
		for (int i = 0; i < next.size(); ++i) {
			tempWeights.set(i, tempWeights.get(i) + learningRate * this.getY() * next.getNeuron(i).getDelta());
		}
	}
	
	public double getY() {
		if (this.inInputLayer) 
			return this.w0;
		return y;
	}
	
	public void calcY(Layer prev) {
		
		double net = 0; // this.w0;
		
		for (Neuron n : prev) {
			net += n.getY() * n.weights.get(this.id);
		}
		
		this.y = sigm(net);
	}
	
	private double sigm(double x) {
		return 1 / (1 + Math.exp(-x));
	}
	
	public void swapWeights() {
		this.weights = new ArrayList<Double>(this.tempWeights);
	}
	
}