package hr.fer.zemris.lab1.fuzzy;

/**
 * Class which models common unary and binary operations over fuzzy sets. 
 * 
 * @author ipaljak
 *
 */

public class Operations {

	public Operations() {
		
	}
	
	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction operation) throws Exception {
		MutableFuzzySet ret = new MutableFuzzySet(set.getDomain());
		for (DomainElement e : set.getDomain()) {
			ret.set(e, operation.valueAt(set.getValueAt(e)));
		}
		return ret;
	}
	
	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction operation) throws Exception {
		MutableFuzzySet ret = new MutableFuzzySet(set1.getDomain());
		for (DomainElement e : set1.getDomain()) {
			ret.set(e, operation.valueAt(set1.getValueAt(e), set2.getValueAt(e)));
		}
		return ret;
	}
	
	
	public static IUnaryFunction zadehNot() {
		return new ZadehNot();
	}
	
	public static IBinaryFunction zadehAnd() {
		return new ZadehAnd();
	}
	
	public static IBinaryFunction zadehOr() {
		return new ZadehOr();
	}
	
	public static IBinaryFunction hamacherTNorm(double v) {
		return new HamacherTNorm(v);
	}
	
	public static IBinaryFunction hamacherSNorm(double v) {
		return new HamacherSNorm(v);
	}
	
	public static class ZadehNot implements IUnaryFunction {
		@Override
		public double valueAt(double x) {
			return 1 - x;
		}
	}
	
	public static class ZadehAnd implements IBinaryFunction {
		@Override
		public double valueAt(double x, double y) {
			return Math.min(x, y);
		}
	}
	
	public static class ZadehOr implements IBinaryFunction {
		@Override
		public double valueAt(double x, double y) {
			return Math.max(x, y);
		}
	}
	
	public static class HamacherTNorm implements IBinaryFunction {
		
		private double v;
	
		public HamacherTNorm(double v) {
			super();
			this.v = v;
		}

		@Override
		public double valueAt(double x, double y) {
			return x * y / (v + (1 - v) * (x + y - x * y));
		}
	}

	public static class HamacherSNorm implements IBinaryFunction {
		
		private double v;
	
		public HamacherSNorm(double v) {
			super();
			this.v = v;
		}

		@Override
		public double valueAt(double x, double y) {
			return (x + y - (2 - v) * x * y) / (1 - (1 - v) * x * y);
		}
	}
	
}
