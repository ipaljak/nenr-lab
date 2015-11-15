package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.IFuzzySet;

/**
 * An interface modeling a defuzzifirer -- a class which offers a
 * service of defuzzification. 
 * 
 * @author ipaljak
 *
 */
public interface Defuzzifier {
	public double defuzzify(IFuzzySet set) throws Exception;
}
