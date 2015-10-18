package hr.fer.zemris.lab1.fuzzy;

import java.util.Iterator;

/**
 * <code>SimpleDomain</code> class is an extension of <code>Domain</code> 
 * which models a subset of integers given as an interval.
 * 
 * @author ipaljak
 *
 */

public class SimpleDomain extends Domain {

	private int first;
	private int last;
	
	public SimpleDomain(int first, int last) {
		super();
		this.first = first;
		this.last = last;
	}
	
	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}

	@Override
	public int getCardinality() {
		return this.last - this.first;
	}
	
	@Override
	public IDomain getComponent(int idx) {
		return this;
	}
	
	@Override
	public int getNumberOfComponents() {
		return 1;
	}
	
	@Override
	public Iterator<DomainElement> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
