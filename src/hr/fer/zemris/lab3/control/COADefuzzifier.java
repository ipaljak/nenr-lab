package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;

/**
 * An implementation of 'Center of Area' defuzzification. 
 * This class implements a <code>Defuzzifier</code> interface.
 * 
 * @author ipaljak
 *
 */

public class COADefuzzifier implements Defuzzifier {

	public COADefuzzifier() {}
	
	@Override
	public double defuzzify(IFuzzySet set) throws Exception {
		double a = 0, b = 0;
		for (DomainElement e : set.getDomain()) {
			a += e.getComponentValue(0) * set.getValueAt(e);
			b += set.getValueAt(e);
		}
		return a / b;
	}
	
}
