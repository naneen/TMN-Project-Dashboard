package com.springapp.mvc.model;

import java.util.Comparator;
import java.util.HashMap;

public class ValueComparator implements Comparator<String> {
    HashMap<String, Integer> base;
    public ValueComparator(HashMap<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) > base.get(b)) {
            return -1;
        }
        else if(base.get(a).equals(base.get(b))){
            if(b.compareTo(a) < 0){
                return -1;
            }
            else{
                return 1;
            }
        }
        else {
            return 1;
        }
    }
}
