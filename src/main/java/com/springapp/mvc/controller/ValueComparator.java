package com.springapp.mvc.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;
    public ValueComparator(HashMap<String, Integer> base) {
        this.base = base;
    }



    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }

}
