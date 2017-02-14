import java.util.HashMap;
import java.util.Map;

import ast.atom.Atom;

public class Environment {
	
	Map<String, Atom> atoms;
	
	Environment() {
		atoms = new HashMap<String, Atom>(); // TODO do we want a hashmap?
	}
	
}
