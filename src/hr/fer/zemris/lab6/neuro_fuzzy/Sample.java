package hr.fer.zemris.lab6.neuro_fuzzy;

public class Sample {

	public double x, y, z;
	
	public Sample(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = (Math.pow(x - 1, 2) + Math.pow(y + 2, 2) - 5 * x * y + 3) * Math.pow(Math.cos(x / 5), 2);
	}
	
}
