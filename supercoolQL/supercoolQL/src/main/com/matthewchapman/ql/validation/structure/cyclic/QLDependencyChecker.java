package com.matthewchapman.ql.validation.structure.cyclic;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.validation.visitor.AbstractQLVisitor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 13/03/2017.
 * <p>
 * Provides circular dependency checking
 *
 * Thanks to Theodore & Yoan for the basic algorithm for closure generation
 */
public class QLDependencyChecker extends AbstractQLVisitor<Void, String> {

    private final QLErrorLogger logger;
    HashSet<Pair> dependencies;

    public QLDependencyChecker() {

        this.logger = new QLErrorLogger();
        this.dependencies = new HashSet<>();
    }

    public QLErrorLogger checkForCircularDependencies(Map<String, Type> typeTable, Map<String, List<Parameter>> expressionMap) {

        dependencies = makeSet(expressionMap);
        HashSet<Pair> closure = makeClosure(dependencies);

        for(Pair pair : closure) {
            if (pair.isReflexive()) {
                logger.addError(expressionMap.get(pair.getStart()).get(0).getLine(), expressionMap.get(pair.getStart()).get(0).getColumn(), pair.getStart(), "Circular reference found");
            }
        }

        return this.logger;
    }

    public HashSet<Pair> makeSet(Map<String, List<Parameter>> expressionMap) {

        HashSet<Pair> dependencySet = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for(Parameter parameter : entry.getValue()) {
                dependencySet.add(new Pair(entry.getKey(), parameter.getID()));
            }
        }

        return dependencySet;
    }

    public HashSet<Pair> generateNewEdges(HashSet<Pair> input) {

        HashSet<Pair> result = new HashSet<>();

        for(Pair pair1 : input) {
            for(Pair pair2 : input) {
                if(pair1.isTransitive(pair2)) {
                    result.add(new Pair(pair1.getEnd(), pair2.getStart()));
                }
            }
        }

        return result;
    }

    public HashSet<Pair> makeClosure(HashSet<Pair> input) {

        HashSet<Pair> closure = new HashSet<>();
        closure.addAll(input);

        while(true) {
            HashSet<Pair> temp = generateNewEdges(closure);
            temp.addAll(closure);

            if(temp.equals(closure)) {
                break;
            }

            closure = temp;
        }

        return closure;
    }
}