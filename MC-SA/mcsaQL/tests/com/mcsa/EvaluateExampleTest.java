package com.mcsa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matt on 06/02/2017.
 */
public class EvaluateExampleTest {
    @Test
    public void evalExampleExpression() throws Exception {
        assertEquals(12, (int)com.mcsa.EvaluateExample.evalExampleExpression("12*(6-5)"));
    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void main() throws Exception {

    }

}