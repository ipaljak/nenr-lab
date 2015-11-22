package hr.fer.zemris.lab4.evolutionary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which models experiment measurements.
 * 
 * @author ipaljak
 *
 */

public class Measurements {

	private List<Double> x, y, fxy;
	
	public Measurements(String fileName) throws NumberFormatException, IOException {
		
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
		fxy = new ArrayList<Double>();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		
		String line; 
		while ((line = bf.readLine()) != null) {
			String words[] = line.split("\\s+");
			x.add(Double.parseDouble(words[0]));
			y.add(Double.parseDouble(words[1]));
			fxy.add(Double.parseDouble(words[2]));
		}
		
		bf.close();
		
	}

	public double evaluateOne(double x, double y, Chromosome c) {
		double[] b = c.getBeta();
		return Math.sin(b[0] + b[1] * x) + b[2] * Math.cos(x * (b[3] + y)) / (1 + Math.exp((x - b[4]) * (x - b[4])));
	}
	
	public List<Double> evaluateAll(Chromosome c) {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0; i < x.size(); ++i) 
			ret.add(evaluateOne(x.get(i), y.get(i), c));
		return ret;
	}
	
	public double getError(List<Double> gxy) {
		double ret = 0;
		for (int i = 0; i < fxy.size(); ++i)
			ret += (fxy.get(i) - gxy.get(i)) * (fxy.get(i) - gxy.get(i));
		return ret / (double) fxy.size();
	}
	
}
