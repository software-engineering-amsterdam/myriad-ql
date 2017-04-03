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

	public void addReference(String name, Type type) {
		references.put(name, type);
	}

	public Type getType(String name) {
		return references.get(name);
	}
	
	public boolean nameExists(String name) {
		return references.containsKey(name);
	}
	
	public Iterator<String> iterator() {		
		return references.keySet().iterator();
        
	}
}
