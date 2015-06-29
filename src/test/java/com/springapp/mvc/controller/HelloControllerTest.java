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
        assertEquals("Central 1",HelloController.fucPlaceCut("True Shop Station in True Coffee Central 1"));
        assertEquals("Central 2",HelloController.fucPlaceCut("True Shop Mini Central 2"));
        assertEquals("Central 3", HelloController.fucPlaceCut("True Shop Central 3"));
        assertEquals("Central 4", HelloController.fucPlaceCut("TrueCoffee @ Central 4"));
        assertEquals("Central 5", HelloController.fucPlaceCut("TrueCoffee Central 5"));
        assertEquals("Central 6",HelloController.fucPlaceCut("True Coffee Central 6"));
        assertEquals("Central 7",HelloController.fucPlaceCut("True Move Shop Central 7"));
        assertEquals("Central 8",HelloController.fucPlaceCut("True Life Central 8"));
        assertEquals("Central 9",HelloController.fucPlaceCut("Central 9"));
    }

    @Test public void testgetPercent(){
        HelloController HelloController= new HelloController();
        assertEquals(26,HelloController.getPercent(50, 190));
        assertEquals(0,HelloController.getPercent(0, 100));
        assertEquals(100,HelloController.getPercent(1, 1));
        assertEquals(33,HelloController.getPercent(1, 3));
        assertEquals(17,HelloController.getPercent(1, 6));

    }
}