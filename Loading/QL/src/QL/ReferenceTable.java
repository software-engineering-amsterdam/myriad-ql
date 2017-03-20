package QL;

import QL.ast.type.Type;

import java.util.HashMap;
import java.util.Map;

public class ReferenceTable {

	private final Map<String, Type> variableType;
	
	public ReferenceTable() {
		variableType = new HashMap<>();
	}

	public void addVariableType(String variable, Type type) {
		variableType.put(variable, type);
	}

	public Type getType(String variable) {
		return variableType.get(variable);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
}
