package com.matthewchapman.ql.validation.structure.cyclic;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 17/03/2017.
 *
 * Tests dependency checks
 */
public class DependencyCheckerTest {

    @Test
    public void testDependencyChecker() {

        DependencyChecker checker = new DependencyChecker();

        int EXPECTED_PAIRS = 6;
        int EXPECTED_REFLEXIVE_PAIRS = 3;
        DependencyPair pair1 = new DependencyPair("test1", "test2");
        DependencyPair pair2 = new DependencyPair("test2", "test3");
        DependencyPair pair3 = new DependencyPair("test3", "test1");

        Set<DependencyPair> dependencySet = new HashSet<DependencyPair>() {{
            add(pair1);
            add(pair2);
            add(pair3);
        }};

        Set<DependencyPair> closure = checker.makeClosure(dependencySet);
        int reflexiveCount = 0;

        for (DependencyPair pair : closure) {
            if (pair.isReflexive()) {
                reflexiveCount++;
            }
        }

        assertEquals(EXPECTED_PAIRS, closure.size());
        assertEquals(EXPECTED_REFLEXIVE_PAIRS, reflexiveCount);
    }
}