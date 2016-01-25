package hr.fer.zemris.lab7.neuro_evolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the dataset used for training our neural net.
 * 
 * @author ipaljak
 *
 */
public class Dataset {

	public class Sample {
		public double x, y, o1, o2, o3;
		public Sample(String line) {
			super();
			String l[] = line.split("\t");
			this.x = Double.parseDouble(l[0]);
			this.y = Double.parseDouble(l[1]);
			this.o1 = Double.parseDouble(l[2]);
			this.o2 = Double.parseDouble(l[3]);
			this.o3 = Double.parseDouble(l[4]);
		}
		
	}
	
	private List<Sample> sampleList; 
	
	public Dataset(String filepath) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		
		sampleList = new ArrayList<Dataset.Sample>();

		String line = "";
		while ((line = reader.readLine()) != null) 
			sampleList.add(new Sample(line));
		
		
	}
	
	public Sample getSample(int i) {
		return sampleList.get(i);
	}

	public List<Sample> getSamples() {
		return this.sampleList;
	}
	
	public double getSize() {
		return (double) this.sampleList.size();	
	}
	
}
