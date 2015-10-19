package hr.fer.zemris.lab1.fuzzy;

import java.util.Arrays;

/**
 * Class which models a single domain element. 
 * 
 * @author ipaljak
 * 
 */

public class DomainElement {

	private int[] values;

	public DomainElement(int ... values) {
		super();
		this.values = values;
	}
	
	public int getNumberOfComponents() {
		return this.values.length;
	}
	
	public int getComponentValue(int i) throws Exception {
		if (i < 0 || i > this.getNumberOfComponents()) 
			throw new Exception("Index out of range!");
		return values[i];
	}

	public static DomainElement of(int ... values) {
		return new DomainElement(values);
	}
	
	public boolean lessThan(DomainElement other) {
		try {
			for (int i = 0; i < this.getNumberOfComponents(); ++i) {
				if (this.getComponentValue(i) != other.getComponentValue(i))
					return this.getComponentValue(i) < other.getComponentValue(i);
			}
		} catch (Exception e) {
			System.err.println("Non comparable domain elements");
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		if (this.getNumberOfComponents() == 1) {
			try {
				return Integer.valueOf(this.getComponentValue(0)).toString();
			} catch (Exception ignorable) {}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		
		for (int i = 0; i < values.length; ++i) {
			sb.append(values[i]);
			if (i != values.length - 1) 
				sb.append(", ");
		}
		
		sb.append(')');
		
		return sb.toString();
	}
	
}
