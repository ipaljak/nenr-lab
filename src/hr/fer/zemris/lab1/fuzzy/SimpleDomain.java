package hr.fer.zemris.lab1.fuzzy;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		return new SimpleDomainIterator();
	}
	
	private class SimpleDomainIterator implements Iterator<DomainElement> {
		
		private int cursor; 
		
		public SimpleDomainIterator() {
			this.cursor = SimpleDomain.this.first;
		}
		
		@Override
		public boolean hasNext() {
			return this.cursor < SimpleDomain.this.last;
		}
		
		@Override
		public DomainElement next() {
			if (this.hasNext()) {
				++cursor;
				return DomainElement.of(cursor - 1);
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
