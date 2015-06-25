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
        map.put("4", 5);
        map.put("6", 5);
        ValueComparator valueCom = new ValueComparator(map);

        assertEquals("'5' is lower than '55', should return 1",1,valueCom.compare("5", "55"));
        assertEquals("'555' is greater than '55', should return -1",-1,valueCom.compare("555", "55"));
        assertEquals("Value of '5' is equal to value of '4' but key '5' is greater than key '4', should return -1",-1,valueCom.compare("5", "4"));
        assertEquals("Value of '4' is equal to value of '6' but key '4' is lower than key '6', should return 1",1,valueCom.compare("4", "6"));
        assertEquals("Value of '6' is equal to value of '4' but key '6' is greater than key '4', should return -1",-1,valueCom.compare("6", "4"));
    }


}