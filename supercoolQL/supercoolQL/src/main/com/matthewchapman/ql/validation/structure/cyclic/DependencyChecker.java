package com.matthewchapman.ql.validation.structure.cyclic;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.core.ErrorLogger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by matt on 13/03/2017.
 * <p>
 * Provides circular dependency checking
 *
 * Thanks to Theodore & Yoan for the basic algorithm for closure generation
 */
public class DependencyChecker {

    private Set<DependencyPair> dependencies;

    public DependencyChecker() {

        this.dependencies = new HashSet<>();
    }

    public ErrorLogger checkForCircularDependencies(Map<String, List<Parameter>> expressionMap) {

        ErrorLogger logger = new ErrorLogger();

        dependencies = makeDependencySet(expressionMap);
        Set<DependencyPair> closure = makeClosure(dependencies);

        for(DependencyPair pair : closure) {
            if (pair.isReflexive()) {
                logger.addError(expressionMap.get(pair.getStart()).get(0).getLine(), expressionMap.get(pair.getStart()).get(0).getColumn(), pair.getStart(), "Circular reference found");
            }
        }

        return logger;
    }

    private Set<DependencyPair> makeDependencySet(Map<String, List<Parameter>> expressionMap) {

        Set<DependencyPair> dependencySet = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for(Parameter parameter : entry.getValue()) {
                dependencySet.add(new DependencyPair(entry.getKey(), parameter.getID()));
            }
        }

        return dependencySet;
    }

    private Set<DependencyPair> generateNewPairs(Set<DependencyPair> input) {

        Set<DependencyPair> result = new HashSet<>();

        for(DependencyPair pair1 : input) {
            for(DependencyPair pair2 : input) {
                if(pair1.isTransitive(pair2)) {
                    result.add(new DependencyPair(pair1.getEnd(), pair2.getStart()));
                }
            }
        }

        return result;
    }

    Set<DependencyPair> makeClosure(Set<DependencyPair> input) {

        Set<DependencyPair> closure = new HashSet<>();
        closure.addAll(input);
        Set<DependencyPair> temp = generateNewPairs(closure);

        while(!temp.equals(closure)) {
            temp.addAll(closure);
            closure = temp;
        }

        return closure;
    }
}