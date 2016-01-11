package hr.fer.zemris.lab5.backend;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which models a feedforward nerual network. Concrete learning
 * algorithms are implemented as extensions of this class.
 *  
 * @author ipaljak
 *
 */

public abstract class NeuralNet {

	protected int maxIter;
	protected int sampleSize;

	protected double learningRate;
	
	protected List<Layer> layers;
	protected List<List<Double>> samples; 
	protected List<List<Integer>> answers;
	
	
	public NeuralNet(String sampleFilePath, int maxIter, int sampleSize, int hiddenLayerSize, int layerCnt, double learningRate) throws Exception {
	
		this.sampleSize = sampleSize;
		this.layers = new ArrayList<>();
		this.maxIter = maxIter; 
		this.learningRate = learningRate;

		if (layerCnt < 3) {
			throw new Exception("Network must have at least 3 layers");
		}
		
		layers.add(new Layer(sampleSize, true, hiddenLayerSize));
	
		for (int i = 0; i < layerCnt - 3; ++i) {
			layers.add(new Layer(hiddenLayerSize, false, hiddenLayerSize));
		}	
	
		layers.add(new Layer(hiddenLayerSize, false, 5));
		layers.add(new Layer(5, false, 0));
	
		samples = new ArrayList<List<Double>>();
		answers = new ArrayList<List<Integer>>();
		
		for (String line : Files.readAllLines(Paths.get(sampleFilePath), Charset.defaultCharset())) {
			
			List<Double> sample = new ArrayList<Double>();
			List<Integer> answer = new ArrayList<Integer>();
			
			String[] words = line.split(" ");
			
			for (int i = 0; i < this.sampleSize; ++i) {
				sample.add(Double.valueOf(words[i]));
			} 
		
			for (int i = this.sampleSize; i < this.sampleSize + 5; ++i) {
				answer.add(Integer.valueOf(words[i]));
			}
			
			samples.add(sample);
			answers.add(answer);
			
		}
		
	}

	private void evaluate(List<Double> sample) {
		
		for (int i = 0; i < sample.size(); ++i) {
			layers.get(0).getNeuron(i).setW0(sample.get(i));
		}
		
		for (int i = 1; i < layers.size(); ++i) {
			layers.get(i).solveLayer(layers.get(i - 1));
		}
	
	}
	
	public double getError() {
		double totalError = 0;
		for (int i = 0; i < samples.size(); ++i) {
			this.evaluate(samples.get(i));
			for (int j = 0; j < 5; ++j) {
				double diff = (double) answers.get(i).get(j) - layers.get(layers.size() - 1).getNeuron(j).getY();
				totalError += diff * diff;
				System.out.println(layers.get(layers.size() - 1).getNeuron(j).getY());
			}
		}
		return totalError;
	}
	
	public abstract void learn();
	
	public void updateWeights(List<List<Double>> samples, List<List<Integer>> answers) {
		
		int outLayer = layers.size() - 1;
		
		for (int i = 0; i < samples.size(); ++i) {
			
			this.evaluate(samples.get(i));
			
			for (int j = 0; j < 5; ++j) {
				double outDelta = Math.pow(layers.get(outLayer).getNeuron(j).getY() - (double) answers.get(i).get(j), 2);
				layers.get(outLayer).getNeuron(j).setDelta(outDelta); 
			}
			
			for (int j = outLayer - 1; j >= 0; --j) {
				layers.get(j).updateWeights(layers.get(j + 1), learningRate);
				layers.get(j).updateDeltas(layers.get(j + 1));
			}
			
		}
		
		for (Layer l : layers) 
			l.swapWeights();
		
	}
	
}
