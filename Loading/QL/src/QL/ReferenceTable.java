package QL;

import QL.ast.type.Type;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReferenceTable implements Iterable<String> {

	private final Map<String, Type> references;
	
	public ReferenceTable() {
		references = new HashMap<>();
	}

	public void addReference(String variable, Type type) {
		references.put(variable, type);
	}

	public Type getType(String variable) {
		return references.get(variable);
	}
	
	public boolean variableExists(String variable) {
		return references.containsKey(variable);
	}
	
	public Iterator<String> iterator() {		
		return references.keySet().iterator();
        
	}
}
