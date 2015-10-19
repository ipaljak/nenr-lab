package hr.fer.zemris.lab1.example;

import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IDomain;

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
	
}
