package QL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import QL.ast.type.Type;

public class ReferenceTable implements Iterable<String> {

	private final Map<String, Type> variableType;
	
	public ReferenceTable() {
		variableType = new HashMap<>();
	}

	public void addReference(String variable, Type type) {
		variableType.put(variable, type);
	}

	public Type getType(String variable) {
		return variableType.get(variable);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
	
	public Iterator<String> iterator() {
		
		return variableType.keySet().iterator();
        
	}
}
