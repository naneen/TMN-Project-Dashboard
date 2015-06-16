package com.springapp.mvc.domain;

/**
 * Created by NapatS on 16/6/2558.
 */
public class Tag {

    public int id;
    public String tagName;

    //getter and setter methods

    public Tag(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
