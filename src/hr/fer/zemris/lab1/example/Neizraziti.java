package hr.fer.zemris.lab1.example;

import hr.fer.zemris.lab1.fuzzy.CalculatedFuzzySet;
import hr.fer.zemris.lab1.fuzzy.Domain;
import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IDomain;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;
import hr.fer.zemris.lab1.fuzzy.MutableFuzzySet;
import hr.fer.zemris.lab1.fuzzy.StandardFuzzySets;

public class Neizraziti {

	public static void main(String[] args) throws Exception {
		
		IDomain d = Domain.intRange(0, 11);
		
		IFuzzySet set1 = new MutableFuzzySet(d)
			.set(DomainElement.of(0), 1.0)
			.set(DomainElement.of(1), 0.8)
			.set(DomainElement.of(2), 0.6)
			.set(DomainElement.of(3), 0.4)
			.set(DomainElement.of(4), 0.2);
		
		Debug.print(set1, "Set1:");

		IDomain d2 = Domain.intRange(-5, 6);
		IFuzzySet set2 = new CalculatedFuzzySet(
				d2,
				StandardFuzzySets.lambdaFunction(
					d2.indexOfElement(DomainElement.of(-4)),
					d2.indexOfElement(DomainElement.of( 0)),
					d2.indexOfElement(DomainElement.of( 4))
				)
		);
		
		Debug.print(set2, "Set2:");
		
	}
	
}
