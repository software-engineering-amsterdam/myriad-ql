package qls.semantic;

import java.util.Map;

import QL.Faults;
import QL.ast.type.Type;

public class Environment {
	
	private Map<String, Type> variableTypes;
	private Map<String, Boolean> variableCovered;
	private Faults faults;
	
	
}
