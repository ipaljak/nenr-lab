package hr.fer.zemris.lab3.control;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;
import hr.fer.zemris.lab1.fuzzy.Operations;
import hr.fer.zemris.lab3.control.Defuzzifier;
import hr.fer.zemris.lab3.control.Rule;

import java.util.ArrayList;


/**
 * An abstract that a fuzzy system for boat control must imlpement
 * 
 * @author ipaljak
 *
 */
public abstract class FuzzySystem {

	protected ArrayList<Rule> rules;
	protected Defuzzifier def;
	
	public FuzzySystem(Defuzzifier def) {
		this.def = def;
		this.rules = new ArrayList<Rule>();
		this.addRules();
	}
	
	protected abstract void addRules();
	
	public double deduce (int[] values) throws Exception {
		
		ArrayList<IFuzzySet> cons = new ArrayList<IFuzzySet>();
		for (Rule r : this.rules) 
			cons.add(r.fire(values));
		
		IFuzzySet union = cons.get(0);
		
		for (int i = 1; i < cons.size(); ++i)
			union = Operations.binaryOperation(union, cons.get(i), Operations.zadehOr());
	
		return def.defuzzify(union);
	}
	
}
