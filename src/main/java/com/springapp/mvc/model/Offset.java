package com.springapp.mvc.model;

/**
 * Created by naneen on 6/15/15 AD.
 */
public class Offset {
    private String name;
    private int percent;

    public Offset(){

    }

    public Offset(String name,int percent){
        this.name = name;
        this.percent = percent;
    }


    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
