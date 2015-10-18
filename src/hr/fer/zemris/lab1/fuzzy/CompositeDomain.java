package hr.fer.zemris.lab1.fuzzy;

import java.util.Iterator;

/**
 * <code>CompositeDomain</code> class models a cartesian product of multiple <code>SimpleDomain</code> instances. 
 * 
 * @author ipaljak
 *
 */
public class CompositeDomain extends Domain{

	private SimpleDomain[] components;
	
	public CompositeDomain(SimpleDomain ... components) {
		super();
		this.components = components;
	}
	
	@Override
	public int getCardinality() {
		int ret = 1;
		for (SimpleDomain component : components) {
			ret *= component.getCardinality();
		}
		return ret;
	}
	
	@Override
	public IDomain getComponent(int idx) {
		try {
			return components[idx];
		} catch (Exception e) {
			System.err.println("Index out of range!");
		}
		return null;
	}
	
	@Override
	public int getNumberOfComponents() {
		return components.length;
	}
	
	@Override
	public Iterator<DomainElement> iterator() {
		return null;
	}
	
}
