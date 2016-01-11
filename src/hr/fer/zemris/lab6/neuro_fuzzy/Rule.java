package hr.fer.zemris.lab6.neuro_fuzzy;

import java.util.Random;

public class Rule {
	
	public double a, b, c, d;
	public double p, q, r;
	
	public double da, db, dc, dd;
	public double dp, dq, dr;
	
	private Random rand = new Random();
	
	public Rule() {
	
		a = 2 * rand.nextDouble() - 1;
		b = 2 * rand.nextDouble() - 1;
		c = 2 * rand.nextDouble() - 1;
		d = 2 * rand.nextDouble() - 1;
		
		p = 2 * rand.nextDouble() - 1;
		q = 2 * rand.nextDouble() - 1;
		r = 2 * rand.nextDouble() - 1;
		
		da = db = dc = dd = dp = dq = dr = 0;
	
	}
	
	public double getF(double x, double y) {
		return p * x + q * y + r;
	}
		
	private double sigmoid(double a, double b, double x) {
		return 1.0 / (1 + Math.exp(b * (x - a)));
	}

	public double getW(double x, double y) {
		return sigmoid(a, b, x) * sigmoid(c, d, y);
	}
	
	public void updateDerivatives(Sample s, double o, double sumW, double sumWZ) {
		
		double w = getW(s.x, s.y);
		double alpha = sigmoid(a, b, s.x);
		double beta = sigmoid(c, d, s.y);
		
		dp += (s.z - o) * w / sumW * s.x;
		dq += (s.z - o) * w / sumW * s.y;
		dr += (s.z - o) * w / sumW;

		da += (s.z - o) * sumWZ / (sumW * sumW) * beta * b * alpha * (1 - alpha);
		db += (s.z - o) * sumWZ / (sumW * sumW) * beta * (a - s.x) * alpha * (1 - alpha);
		dc += (s.z - o) * sumWZ / (sumW * sumW) * alpha * d * beta * (1 - beta);
		dd += (s.z - o) * sumWZ / (sumW * sumW) * alpha * (c - s.y) * beta * (1 - beta);
	
	}
	
	public void update(double eta) {
		
		p += eta * dp; 
		q += eta * dq; 
		r += eta * dr;
		
		a += eta * da; 
		b += eta * db; 
		c += eta * dc; 
		d += eta * dd;
		
		da = db = dc = dd = dp = dq = dr = 0;
	
	}
	
}
