package hr.fer.zemris.lab1.fuzzy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
		return new CompositeDomainIterator();
	}
	
	private class CompositeDomainIterator implements Iterator<DomainElement> {
		
		private boolean done;
		
		private int cnt;
		private int[] cursorValue;
		
		public CompositeDomainIterator() {
			done = false;
			cnt = CompositeDomain.this.getNumberOfComponents();
			cursorValue = new int[cnt];
			for (int i = 0; i < cnt; ++i) {
				cursorValue[i] = ((SimpleDomain) CompositeDomain.this.getComponent(i)).getFirst();
			}
		} 
		
		private boolean finished() {
			for (int i = 0; i < cnt; ++i) {
				if (cursorValue[i] < ((SimpleDomain) CompositeDomain.this.getComponent(i)).getLast() - 1) 
					return false;
			}
			return true;
		}
		
		@Override
		public boolean hasNext() {
			return !this.done;
		}
		
		@Override
		public DomainElement next() {
			if (this.hasNext()) {
				int[] oldCursor = new int[cnt];
				oldCursor = Arrays.copyOf(cursorValue, cnt);
				DomainElement ret = DomainElement.of(oldCursor);
				this.done = finished();
				for (int i = cnt - 1; i >= 0; --i) {
					if (cursorValue[i] == ((SimpleDomain) CompositeDomain.this.getComponent(i)).getLast() - 1) 
						continue;
					++cursorValue[i];
					for (int j = i + 1; j < cnt; ++j) 
						cursorValue[j] = ((SimpleDomain) CompositeDomain.this.getComponent(j)).getFirst();
					break;
				}
				return ret;
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
