package com.thehoistory.learn.dao;


import com.thehoistory.learn.model.Course;
import com.thehoistory.learn.exc.DaoException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCourseDao implements CourseDao{

    private Sql2o sql2o;

    public Sql2oCourseDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void add(Course course) throws DaoException {
        String sql = "INSERT INTO courses(name, url) VALUES (:name, :url)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(course)
                    .executeUpdate()
                    .getKey();
            course.setId(id);
        } catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem adding course");
        }

    }

    public List<Course> findAll() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM courses")
                    .executeAndFetch(Course.class);
        }

    }


}
