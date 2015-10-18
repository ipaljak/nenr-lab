package hr.fer.zemris.lab1.fuzzy;

/**
 * Abstract implementation of <code>IDomain</code> interface.
 *  
 * @author ipaljak
 *
 */

public abstract class Domain implements IDomain {

	public static IDomain intRange(int first, int last) {
		return new SimpleDomain(first, last);
	}
	
	public static Domain combine(IDomain a, IDomain b) {
		return new CompositeDomain((SimpleDomain) a, (SimpleDomain) b);
	}
	
	@Override
	public int indexOfElement(DomainElement element) {
		int ret = 0;
		try {
			for (DomainElement curr : this) {
				if (curr.equals(element))
					return ret;
				++ret;
			}
		} catch (Exception e) {
			System.err.println("Element outside of domain!");
		}
		return 0;
	}
	
	@Override
	public DomainElement elementForIndex(int idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
