package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.IFuzzySet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class models a fuzzy system which models the acceleration controls (gas and break pedals)
 * .
 * @author ipaljak
 *
 */

public class AcceleratorFuzzySystem extends FuzzySystem {

	public AcceleratorFuzzySystem(Defuzzifier def) {
		super(def);
	}
	
	@Override
	protected void addRules() {
		
		// If going too slow then speed up
		IFuzzySet[] a1 = {null, null, null, null, Sets.GOING_SLOW, null};
		IFuzzySet b1 = Sets.SPEED_UP;
		
		rules.add(new Rule(new ArrayList<IFuzzySet>(Arrays.asList(a1)), b1));
	
		// If going too fast then slow down
		IFuzzySet[] a2 = {null, null, null, null, Sets.GOING_FAST, null};
		IFuzzySet b2 = Sets.SLOW_DOWN;
		
		rules.add(new Rule(new ArrayList<IFuzzySet>(Arrays.asList(a2)), b2));

		
	}
	
}
