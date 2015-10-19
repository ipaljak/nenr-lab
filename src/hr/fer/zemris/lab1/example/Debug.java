package hr.fer.zemris.lab1.example;

import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IDomain;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;

public class Debug {

	public static void print(IDomain domain, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : domain) {
			System.out.println("Element domene: " + e);
		}
		System.out.println("Kardinalitet domene je: " + domain.getCardinality());
		System.out.println();
	}
	
	public static void print(IFuzzySet set, String headingText) throws Exception {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : set.getDomain()) {
			System.out.println("d(" + e.getComponentValue(0) + ")="+set.getValueAt(e));
		}
		System.out.println();
	}
	
}
