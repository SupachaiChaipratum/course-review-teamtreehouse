package com.thehoistory.learn.model;

import lombok.Data;

@Data
public class Review {
    private int id;
    private int courseId;
    private int rating;
    private String comment;
}
