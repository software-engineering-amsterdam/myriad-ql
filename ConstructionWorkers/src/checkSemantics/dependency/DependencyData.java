package checkSemantics.dependency;

import ASTnodes.expressions.literals.Identifier;

import java.util.*;

/**
 * Created by LGGX on 19-Feb-17.
 */
public class DependencyData {

    private final Map<Identifier, List<Identifier>> dependencyList;

    public DependencyData() {
        this.dependencyList = new HashMap<>();
    }

    public Set<Identifier> getIds() {
        return this.dependencyList.keySet();
    }

    public List<Identifier> getIdDependencies(Identifier id) {
        return this.dependencyList.get(id);
    }

    public List<String> getIdDependencyNames(Identifier id) {
        List<String> names = new ArrayList<>();
        if (this.dependencyList.get(id) != null) {
            for (Identifier dependency : this.dependencyList.get(id)) {
                names.add(dependency.getName());
            }
        }
        return names;
    }

    public void addIdDependenant(Identifier id, Identifier dependant) {
        List<Identifier> dependandtsList = this.dependencyList.get(id);
        if (dependandtsList == null) {
            dependandtsList = new ArrayList<>();
        }
        dependandtsList.add(dependant);
        this.dependencyList.put(id, dependandtsList);
    }

    public String toString() {
        String returnString = "";
        for (Identifier dependee : this.dependencyList.keySet()) {
            returnString += "\n" + dependee.toString()
                    + this.dependencyList.get(dependee).toString();
        }
        return returnString;
    }
}
