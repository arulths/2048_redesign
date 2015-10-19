package com.group2;

import junit.framework.TestCase;

/**
 * Created by Josh Voskamp on 10/5/2015.
 */
public class MathTest extends TestCase {
    private  Math test;
    public void setUp() throws Exception {
        test = new Math();
    }

    public void testAdd() throws Exception {
        assertEquals(test.add(1, 2), 3);
        assertEquals(test.add(1,1),2);
    }
}