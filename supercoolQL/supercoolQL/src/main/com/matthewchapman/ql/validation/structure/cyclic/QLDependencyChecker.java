package com.matthewchapman.ql.validation.structure.cyclic;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.validation.visitor.AbstractQLVisitor;

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
public class QLDependencyChecker extends AbstractQLVisitor<Void, String> {

    private Set<QLDependencyPair> dependencies;

    public QLDependencyChecker() {

        this.dependencies = new HashSet<>();
    }

    public QLErrorLogger checkForCircularDependencies(Map<String, List<Parameter>> expressionMap) {

        QLErrorLogger logger = new QLErrorLogger();

        dependencies = makeSet(expressionMap);
        Set<QLDependencyPair> closure = makeClosure(dependencies);

        for(QLDependencyPair pair : closure) {
            if (pair.isReflexive()) {
                logger.addError(expressionMap.get(pair.getStart()).get(0).getLine(), expressionMap.get(pair.getStart()).get(0).getColumn(), pair.getStart(), "Circular reference found");
            }
        }

        return logger;
    }

    private Set<QLDependencyPair> makeSet(Map<String, List<Parameter>> expressionMap) {

        Set<QLDependencyPair> dependencySet = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for(Parameter parameter : entry.getValue()) {
                dependencySet.add(new QLDependencyPair(entry.getKey(), parameter.getID()));
            }
        }

        return dependencySet;
    }

    private Set<QLDependencyPair> generateNewEdges(Set<QLDependencyPair> input) {

        Set<QLDependencyPair> result = new HashSet<>();

        for(QLDependencyPair pair1 : input) {
            for(QLDependencyPair pair2 : input) {
                if(pair1.isTransitive(pair2)) {
                    result.add(new QLDependencyPair(pair1.getEnd(), pair2.getStart()));
                }
            }
        }

        return result;
    }

    Set<QLDependencyPair> makeClosure(Set<QLDependencyPair> input) {

        Set<QLDependencyPair> closure = new HashSet<>();
        closure.addAll(input);

        while(true) {
            Set<QLDependencyPair> temp = generateNewEdges(closure);
            temp.addAll(closure);

            if(temp.equals(closure)) {
                break;
            }

            closure = temp;
        }

        return closure;
    }
}