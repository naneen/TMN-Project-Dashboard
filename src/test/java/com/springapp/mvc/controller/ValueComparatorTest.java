package com.springapp.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Bas on 25/6/2558.
 */
public class ValueComparatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCompare() throws Exception {
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        map.put("55", 55);
        map.put("555", 555);
        map.put("5", 5);
        ValueComparator valueCom = new ValueComparator(map);

        assertEquals("'5' is lower than '55', should return 1",1,valueCom.compare("5", "55"));
        assertEquals("'555' is greater than '55', should return -1",-1,valueCom.compare("555", "55"));
        assertEquals("'555' is equal to '555', should return -1",-1,valueCom.compare("555", "555"));

    }


}