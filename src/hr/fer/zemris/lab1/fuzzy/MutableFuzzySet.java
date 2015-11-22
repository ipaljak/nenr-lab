package hr.fer.zemris.lab1.fuzzy;

/**
 * This class models a mutable fuzzy set which stores membership function values in an array of doubles. 
 * 
 * @author ipaljak
 *
 */
public class MutableFuzzySet implements IFuzzySet {

	private IDomain domain;
	private double[] memberships;
	
	public MutableFuzzySet(IDomain domain) {
		this.domain = domain;
		memberships = new double[domain.getCardinality()];
		for (DomainElement e : domain) {
			memberships[domain.indexOfElement(e)] = this.getValueAt(e);
		}
	}
	
	public MutableFuzzySet set(DomainElement e, double val) {
		this.memberships[domain.indexOfElement(e)] = val;
		return this;
	}
	
	@Override
	public double getValueAt(DomainElement e) {
		return memberships[this.domain.indexOfElement(e)];
	}
	
	@Override
	public IDomain getDomain() {
		return this.domain;
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
