package QL.semantic;

import QL.Faults;
import QL.ReferenceTable;

public class Environment {
	
	private ReferenceTable variables;
	private Faults faults;
	
	public Environment() {
		this.variables = new ReferenceTable();
		this.faults = new Faults();
	}
	
	ReferenceTable getReferenceTable() {
		return variables;
	}
	
	Faults getFaults() {
		return faults;
	}

}
