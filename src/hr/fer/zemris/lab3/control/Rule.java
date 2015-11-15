package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;

import java.util.ArrayList;

/**
 * Class which models a single if-then rule. 
 * 
 * @author ipaljak
 *
 */

public class Rule {

	private ArrayList<IFuzzySet> antecedent;
	private IFuzzySet consequent;
	
	public Rule(ArrayList<IFuzzySet> antecedent, IFuzzySet consequent) {
		super();
		this.antecedent = antecedent;
		this.consequent = consequent;
	} 
	
	public IFuzzySet fire(int[] values) {
		double mu = 1;
		for (int i = 0; i < antecedent.size(); ++i) {
			if (antecedent.get(i) == null) continue;
			mu = Math.min(mu, antecedent.get(i).getValueAt(DomainElement.of(values[i]))); 
		}
		return consequent.cutAt(mu);
	}
	
}
