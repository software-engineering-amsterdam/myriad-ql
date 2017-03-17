package com.matthewchapman.ql.validation.structure.cyclic;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 17/03/2017.
 */
public class QLDependencyCheckerTest {

    @Test
    public void testDependencyChecker() {

        QLDependencyChecker checker = new QLDependencyChecker();

        int EXPECTED_PAIRS = 6;
        int EXPECTED_REFLEXIVE_PAIRS = 3;
        QLDependencyPair pair1 = new QLDependencyPair("test1", "test2");
        QLDependencyPair pair2 = new QLDependencyPair("test2", "test3");
        QLDependencyPair pair3 = new QLDependencyPair("test3", "test1");

        Set<QLDependencyPair> dependencySet = new HashSet<QLDependencyPair>(){{add(pair1); add(pair2); add(pair3);}};

        Set<QLDependencyPair> closure = checker.makeClosure(dependencySet);
        int reflexiveCount = 0;

        for (QLDependencyPair pair : closure) {
            if (pair.isReflexive()) {
                reflexiveCount++;
            }
        }

        assertEquals(EXPECTED_PAIRS, closure.size());
        assertEquals(EXPECTED_REFLEXIVE_PAIRS, reflexiveCount);
    }
}