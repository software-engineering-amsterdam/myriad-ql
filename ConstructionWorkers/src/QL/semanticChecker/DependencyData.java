/**
 * DependencyData.java.
 *
 * TODO: check.
 */

package QL.semanticChecker;

import java.util.*;

public class DependencyData {

    private final Map<String, List<String>> dependencyList;

    public DependencyData() {
        this.dependencyList = new HashMap<>();
    }

    public Set<String> getKeys() {
        return dependencyList.keySet();
    }

    public List<String> getDependencies(String id) {
        return dependencyList.get(id);
    }

    public List<String> getDependencyNames(String id) {
        List<String> names = new ArrayList<>();

        if (dependencyList.get(id) != null) {
            for (String dependency : dependencyList.get(id)) {
                names.add(dependency);
            }
        }
        return names;
    }

    public void addDependency(String id, String dependant) {
        List<String> dependList = dependencyList.get(id);

        if (dependList == null) {
            dependList = new ArrayList<>();
        }
        dependList.add(dependant);
        dependencyList.put(id, dependList);
    }
}
