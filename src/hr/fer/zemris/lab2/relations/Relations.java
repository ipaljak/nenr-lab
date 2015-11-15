package hr.fer.zemris.lab2.relations;

import hr.fer.zemris.lab1.fuzzy.CompositeDomain;
import hr.fer.zemris.lab1.fuzzy.DomainElement;
import hr.fer.zemris.lab1.fuzzy.IDomain;
import hr.fer.zemris.lab1.fuzzy.IFuzzySet;
import hr.fer.zemris.lab1.fuzzy.MutableFuzzySet;
import hr.fer.zemris.lab1.fuzzy.SimpleDomain;

/**
 * Class which offers some static methods that can 
 * dentify the type of a given relation
 *
 * @author ipaljak
 *
 */

public class Relations {

	public static boolean isUtimesURelation(IFuzzySet relation) {
		return relation.getDomain().getNumberOfComponents() == 2;
	}
	
	public static boolean isSymmetric(IFuzzySet relation) throws Exception {
		if (!Relations.isUtimesURelation(relation))
			return false;
		for (DomainElement e : relation.getDomain()) {
			int fst = e.getComponentValue(0), snd = e.getComponentValue(1);
			DomainElement sim = DomainElement.of(snd,fst);
			if (relation.getValueAt(e) != relation.getValueAt(sim))
				return false;
		}
		return true;
	}
	
	public static boolean isReflexive(IFuzzySet relation) throws Exception {
		if (!Relations.isUtimesURelation(relation))
			return false;
		for (DomainElement e : relation.getDomain()) {
			int fst = e.getComponentValue(0), snd = e.getComponentValue(1);
			if (fst == snd && relation.getValueAt(e) != 1)
				return false;
		}
		return true;
	}
	
	public static boolean isMaxMinTransitive(IFuzzySet relation) throws Exception {
		if (!Relations.isUtimesURelation(relation))
			return false;
		for (DomainElement e1 : relation.getDomain()) {
			int fst1 = e1.getComponentValue(0), snd1 = e1.getComponentValue(1);
			for (DomainElement e2 : relation.getDomain()) {
				int fst2 = e2.getComponentValue(0), snd2 = e2.getComponentValue(1);
				if (snd1 != fst2) continue;
				if (relation.getValueAt(DomainElement.of(fst1, snd2)) < Math.min(relation.getValueAt(e1), relation.getValueAt(e2)))
					return false;
			}
		}
		return true;
	}
	
	public static boolean isFuzzyEquivalence(IFuzzySet relation) throws Exception {
		return Relations.isReflexive(relation) && 
				Relations.isSymmetric(relation) && 
				Relations.isMaxMinTransitive(relation);
	}
	
	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) throws Exception {
		
		IDomain u = r1.getDomain().getComponent(0),
				v = r1.getDomain().getComponent(1),
				w = r2.getDomain().getComponent(1);
		
		MutableFuzzySet ret = new MutableFuzzySet(new CompositeDomain((SimpleDomain) u, (SimpleDomain) w));
		
		for (DomainElement x : u) {
			for (DomainElement y : w) {
				double val = 0.0;
				for (DomainElement z : v) 
					val = Math.max(val, Math.min(
							r1.getValueAt(DomainElement.of(x.getComponentValue(0), z.getComponentValue(0))),
							r2.getValueAt(DomainElement.of(z.getComponentValue(0), y.getComponentValue(0)))));
				ret.set(DomainElement.of(x.getComponentValue(0), y.getComponentValue(0)), val);
			}
		}
		
		return ret;
		
	}
	
}
