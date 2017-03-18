package com.thehoistory.learn.dao;


import com.thehoistory.learn.model.Course;
import com.thehoistory.learn.exc.DaoException;

import java.util.List;

public interface CourseDao {
    void add(Course course) throws DaoException;

    List<Course> findAll();

    Course findById(int id);
}
