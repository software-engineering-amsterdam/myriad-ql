package com.mcsa;

import static org.junit.Assert.*;

/**
 * Created by matt on 06/02/2017.
 */
public class MainTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void main() throws Exception {
        Main.main(null);
        assertEquals(3, Main.i);
    }

}