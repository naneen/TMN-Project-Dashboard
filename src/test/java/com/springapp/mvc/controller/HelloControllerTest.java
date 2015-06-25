package com.springapp.mvc.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bas on 25/6/2558.
 */
public class HelloControllerTest {

    @Test
    public void testPlaceCut() {
        HelloController HelloController = new HelloController();
        assertEquals("Central 1",HelloController.PlaceCut("True Shop Station in True Coffee Central 1"));
        assertEquals("Central 2",HelloController.PlaceCut("True Shop Mini Central 2"));
        assertEquals("Central 3", HelloController.PlaceCut("True Shop Central 3"));
        assertEquals("Central 4", HelloController.PlaceCut("TrueCoffee @ Central 4"));
        assertEquals("Central 5", HelloController.PlaceCut("TrueCoffee Central 5"));
        assertEquals("Central 6",HelloController.PlaceCut("True Coffee Central 6"));
        assertEquals("Central 7",HelloController.PlaceCut("True Move Shop Central 7"));
        assertEquals("Central 8",HelloController.PlaceCut("True Life Central 8"));
    }

    @Test public void testgetPercent(){
        HelloController HelloController= new HelloController();
        assertEquals(26,HelloController.getPercent(50, 190));
    }
}