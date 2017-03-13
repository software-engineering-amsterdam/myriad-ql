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

        DependencySet actualClosure = resolver.transitiveClosure();

        assertEquals(9, actualClosure.size());
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("1"), new Identifier("2"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("1"), new Identifier("3"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("1"), new Identifier("1"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("2"), new Identifier("1"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("2"), new Identifier("2"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("2"), new Identifier("3"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("3"), new Identifier("3"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("3"), new Identifier("2"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("3"), new Identifier("1"))));
    }

    @Test
    public void shouldReturnCircularDependenciesOnly() {
        CircularDependenciesResolver resolver = new CircularDependenciesResolver() {{
            register(new DependencyPair(new Identifier("1"), new Identifier("2")));
            register(new DependencyPair(new Identifier("2"), new Identifier("3")));
            register(new DependencyPair(new Identifier("3"), new Identifier("1")));
        }};

        DependencySet actualClosure = resolver.circularDependencies();

        assertEquals(3, actualClosure.size());
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("1"), new Identifier("1"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("3"), new Identifier("3"))));
        assertTrue(actualClosure.contains(new DependencyPair(new Identifier("2"), new Identifier("2"))));
    }
}