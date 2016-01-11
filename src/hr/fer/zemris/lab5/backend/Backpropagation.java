package hr.fer.zemris.lab5.backend;

public class Backpropagation extends NeuralNet {

	public Backpropagation(String sampleFilePath, int maxIter, int sampleSize,
			int hiddenLayerSize, int layerCnt, double learningRate)
			throws Exception {
		super(sampleFilePath, maxIter, sampleSize, hiddenLayerSize, layerCnt,
				learningRate);
	}

	@Override
	public void learn() {
		
		while (this.maxIter-- > 0) {
			
			double error = this.getError();
			System.out.println(error);
			
			updateWeights(samples, answers);
			
		}
	}
	
}
