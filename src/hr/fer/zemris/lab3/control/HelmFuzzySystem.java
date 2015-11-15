package hr.fer.zemris.lab3.control;
import java.util.ArrayList;
import java.util.Arrays;

import hr.fer.zemris.lab1.fuzzy.IFuzzySet;
import hr.fer.zemris.lab3.control.Defuzzifier;
import hr.fer.zemris.lab3.control.Rule;
import hr.fer.zemris.lab3.control.Sets;

/**
 * Class models a fuzzy system which controls the helm.
 * 
 * @author ipaljak
 *
 */

public class HelmFuzzySystem extends FuzzySystem {

	public HelmFuzzySystem(Defuzzifier def) {
		super(def);
	}

	@Override
	protected void addRules() {
		
		// if close to left edge then turn right
		IFuzzySet[] a1 = {null, null, Sets.NEAR_SHORE, null, null, null};
		IFuzzySet b1 = Sets.SHARP_RIGHT;
		
		rules.add(new Rule(new ArrayList<IFuzzySet>(Arrays.asList(a1)), b1));
		
		// If close to right edge then turn left
		IFuzzySet[] a2 = {null, null, null, Sets.NEAR_SHORE, null, null};
		IFuzzySet b2 = Sets.SHARP_LEFT;
		
		rules.add(new Rule(new ArrayList<IFuzzySet>(Arrays.asList(a2)), b2));
	
		// Turn around if going in the wrong way
		IFuzzySet[] a3 = {null, null, null, null, null, Sets.WRONG_WAY};
		IFuzzySet b3 = Sets.SHARP_LEFT;
		
		rules.add(new Rule(new ArrayList<IFuzzySet>(Arrays.asList(a3)), b3));
		
	}
		
}
