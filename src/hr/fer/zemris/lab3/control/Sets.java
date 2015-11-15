package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.CalculatedFuzzySet;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;
import hr.fer.zemris.lab1.fuzzy.StandardFuzzySets;

/**
 * Class provides useful fuzzy sets for boat control. 
 * 
 * @author ipaljak
 *
 */

public class Sets {

	public static final IFuzzySet TURN_LEFT = new CalculatedFuzzySet(Domains.ANGLE, StandardFuzzySets.gammaFunction(110, 130));
	public static final IFuzzySet TURN_RIGHT = new CalculatedFuzzySet(Domains.ANGLE, StandardFuzzySets.lFunction(50, 70));
	
	public static final IFuzzySet SHARP_LEFT = new CalculatedFuzzySet(Domains.ANGLE, StandardFuzzySets.gammaFunction(150, 180));
	public static final IFuzzySet SHARP_RIGHT = new CalculatedFuzzySet(Domains.ANGLE, StandardFuzzySets.lFunction(0, 30));
	
	public static final IFuzzySet NEAR_SHORE = new CalculatedFuzzySet(Domains.DISTANCE, StandardFuzzySets.lFunction(30, 50));
	
}
