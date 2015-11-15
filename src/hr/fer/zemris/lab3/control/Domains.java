package hr.fer.zemris.lab3.control;

import hr.fer.zemris.lab1.fuzzy.IDomain;
import hr.fer.zemris.lab1.fuzzy.SimpleDomain;

/**
 * Class provides useful domains for boat control. 
 * 
 * @author ipaljak
 *
 */

public class Domains {
	
	public static final IDomain ANGLE = new SimpleDomain(-90, 91);
	public static final IDomain DISTANCE = new SimpleDomain(0, 1301);

}
