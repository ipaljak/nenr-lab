package hr.fer.zemris.lab1.fuzzy;

/**
 * A fuzzy set is defined using a membership function over some domain elements. 
 * This definition is being modelled using <code>IFuzzySet</code> interface. 
 * 
 * @author ipaljak
 *
 */
public interface IFuzzySet {

	public IDomain getDomain();
	
	public double getValueAt(DomainElement e);
	
}
