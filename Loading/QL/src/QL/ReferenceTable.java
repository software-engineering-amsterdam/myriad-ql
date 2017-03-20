package QL;

import QL.ast.type.Type;
import QL.ast.type.UnknownType;
import QL.errorhandling.Error;

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
	
	public Type getType(String variable, int line) {
		
		if (!variableExists(variable)) {
	        return new UnknownType(line);
		}
		return variableType.get(variable);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
	
	public Map<String, Type> getVariableTypes() {
		return variableType;
	}
}
