package com.springapp.mvc;

import com.springapp.mvc.controller.HelloController;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by James on 6/25/15 AD.
 */
public class UnitTest {
    @Test
    public void test_getpercent(){
        assertEquals(91, HelloController.getPercent(10,11));
        assertEquals(0, HelloController.getPercent(0,1000));
        assertEquals(50, HelloController.getPercent(55,110));
        assertEquals(50, HelloController.getPercent(55,110));
    }
}
