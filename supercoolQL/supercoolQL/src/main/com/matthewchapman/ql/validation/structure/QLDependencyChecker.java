package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.validation.visitor.AbstractQLVisitor;

import java.util.List;
import java.util.Map;

/**
 * Created by matt on 13/03/2017.
 * <p>
 * Provides type checking and missing parameter checking
 */
public class QLDependencyChecker extends AbstractQLVisitor<Void> {

    private final QLErrorLogger logger;

    public QLDependencyChecker() {

        this.logger = new QLErrorLogger();
    }

    //TODO it works, but it's not nice.
    public QLErrorLogger checkForCircularDependencies(Map<String, Type> typeTable, Map<String, List<Parameter>> expressionMap) {

//        for (HashMap.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
//
//            List<Parameter> parameters = new ArrayList<>(entry.getValue());
//
//            for (Parameter parameter : parameters) {
//                if (expressionMap.containsKey(parameter.getID())) {
//                    expressionMap.get(parameter.getID()).addAll(parameters);
//
//                    if (expressionMap.get(parameter.getID()).contains(parameter.getID())) {
//                        //System.err.println(expressionMap.);
//                        break;
//                    }
//
//                }
//            }
//        }

        return this.logger;
    }

}
