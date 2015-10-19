package hr.fer.zemris.lab1.fuzzy;


/**
 * IDomain interface models the domain and all of its functionalities.
 * 
 * @author ipaljak
 * @param <T>
 *
 */

public interface IDomain extends Iterable<DomainElement> {

	public int getCardinality();
	
	public IDomain getComponent(int idx);
	
	public int getNumberOfComponents();
	
	public int indexOfElement(DomainElement element);
	
	public DomainElement elementForIndex(int idx);
	
}
