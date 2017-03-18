package com.thehoistory.learn.model.com.thehoistory.learn.dao;


import com.thehoistory.learn.model.Review;
import com.thehoistory.learn.model.com.thehoistory.learn.exc.DaoException;

import java.util.List;

public interface ReviewDao {
    void add(Review review) throws DaoException;

    List<Review> findAll();

    List<Review> findByCourseId(int courseId);

}
