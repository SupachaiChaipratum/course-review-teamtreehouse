package com.thehoistory.learn.model;

import lombok.Data;

@Data
public class Course {
    private  int id;
    private String name;
    private String url;

    public Course(String name,String url){
        this.name = name;
        this.url = url;
    }
}
