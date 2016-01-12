package hr.fer.zemris.lab5.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class models a single neuron layer in an artificial neural network.
 * 
 * @author ipaljak
 *
 */

public class Layer implements Iterable<Neuron> {

	private List<Neuron> neurons; 
	
	public Layer(int size, boolean isInputLayer, int nextLayerSize) {
		neurons = new ArrayList<Neuron>();
		fillLayer(size, isInputLayer, nextLayerSize);
	}
	
	public void fillLayer(int size, boolean isInputLayer, int nextLayerSize) {
		for (int i = 0; i < size; ++i) {
			this.addNeuron(new Neuron(isInputLayer, nextLayerSize, i));
		}
	}
	
	public void addNeuron(Neuron n) {
		neurons.add(n);
	}
	
	public Neuron getNeuron(int i) {
		return neurons.get(i);
	}
	
	public void solveLayer(Layer prev) {
		for (Neuron n : this) {
			n.calcY(prev);
		}
	}
	
	public int size() {
		return neurons.size();
	}
	
	public void updateDeltas(Layer next) {
		for (Neuron n : this) {
			n.setDelta(next);
		}
	}
	
	public void updateWeights(Layer next, double learningRate) {
		for (Neuron n : this) {
			n.updateWeights(next, learningRate);
		}
	}
	
	public void swapWeights() {
		for (Neuron n : this) 
			n.swapWeights();
	}
	
	@Override
	public Iterator<Neuron> iterator() {
		return neurons.iterator();
	}
	
	
	
}