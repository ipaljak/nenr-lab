package hr.fer.zemris.lab1.fuzzy;

/**
 * Implements a fuzzy set defined by its domain and a reference to a membership function. 
 * 
 * @author ipaljak
 */

public class CalculatedFuzzySet implements IFuzzySet{

	private IDomain domain;
	private IIntUnaryFunction function;
	
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
		this.domain = domain;
		this.function = function;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}
	
	@Override
	public double getValueAt(DomainElement e) {
		return function.valueAt(domain.indexOfElement(e));
	}
	
	@Override
	public IFuzzySet cutAt(double mu) {
		MutableFuzzySet ret = new MutableFuzzySet(this.domain);
		for (DomainElement e : this.domain) {
				ret = ret.set(e, this.getValueAt(e) * mu);
		}
		return ret;
	}
	
}
