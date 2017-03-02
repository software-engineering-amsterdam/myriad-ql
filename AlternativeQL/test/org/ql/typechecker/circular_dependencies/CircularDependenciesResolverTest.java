package org.ql.typechecker.circular_dependencies;

import org.junit.Test;
import org.ql.ast.Identifier;

import static org.junit.Assert.*;

public class CircularDependenciesResolverTest {
    @Test
    public void shouldReturnTransitiveClosureOnSetOfIdentifierPairs() {
        CircularDependenciesResolver resolver = new CircularDependenciesResolver() {{
            register(new DependencyPair(new Identifier("1"), new Identifier("2")));
            register(new DependencyPair(new Identifier("2"), new Identifier("3")));
            register(new DependencyPair(new Identifier("3"), new Identifier("1")));
        }};

        System.out.println(resolver.findTransitiveClosure().toString());
    }
}