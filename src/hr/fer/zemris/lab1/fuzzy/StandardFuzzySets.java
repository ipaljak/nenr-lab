package hr.fer.zemris.lab1.fuzzy;

/**
 * Helper class <code>StandardFuzzySets<code> models standard membership functions for fuzzy sets.
 * More precisely, it models l-function, lambda-function and gamma-function.
 * 
 * @author ipaljak
 *
 */
public class StandardFuzzySets {

	public StandardFuzzySets() {
		
	}
	
	public static IIntUnaryFunction lFunction(int a, int b) {
		return new lFunction(a, b);
	}
	
	public static IIntUnaryFunction gammaFunction(int a, int b) {
		return new gammaFunction(a, b);
	}
	
	public static IIntUnaryFunction lambdaFunction(int a, int b, int c) {
		return new lambdaFunction(a, b, c);
	}
	
	private static class lFunction implements IIntUnaryFunction {
		
		private int a, b;

		public lFunction(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
		@Override
		public double valueAt(int idx) {
			if (idx < this.a) return 1;
			if (idx >= this.b) return 0;
			return (double) (this.b - idx) / (this.b - this.a);
		}
		
	}
	
	private static class gammaFunction implements IIntUnaryFunction { 
		
		private int a, b;

		public gammaFunction(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
		@Override
		public double valueAt(int idx) {
			if (idx < this.a) return 0;
			if (idx >= this.b) return 1;
			return (double) (idx - this.a) / (this.b - this.a);
		}
		
	}
		
		
	private static class lambdaFunction implements IIntUnaryFunction {
		
		private int a, b, c;

		public lambdaFunction(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		@Override
		public double valueAt(int idx) {
			if (idx < this.a) return 0;
			if (this.a <= idx && idx < this.b) return (double) (idx - this.a) / (this.b - this.a);
			if (this.b <= idx && idx < this.c) return (double) (this.c - idx) / (this.c - this.b);
			return 0;
		}
		
	}
	
	
}
