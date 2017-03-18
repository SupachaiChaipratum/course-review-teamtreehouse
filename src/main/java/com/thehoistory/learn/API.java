package com.thehoistory.learn;


import com.google.gson.Gson;
import com.thehoistory.learn.dao.CourseDao;
import com.thehoistory.learn.dao.Sql2oCourseDao;
import com.thehoistory.learn.model.Course;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class API {
    public static void main(String[] args){
        Sql2o sql2o = new Sql2o("jdbc:h2:~/review.db;INIT=RUNSCRIPT from 'classpath:db/init.sql");
        CourseDao courseDao = new Sql2oCourseDao(sql2o);

        Gson gson = new Gson();

        post("/courses","application/json" , (req, res)  -> {
            Course course = gson.fromJson(req.body(), Course.class);
            courseDao.add(course);
            res.status(201);
            res.type("application/json");
            return course;
        },gson::toJson);


        get("/courses", "application/json",
                (req, res) -> courseDao.findAll(), gson::toJson);

        get("/courses/:id" ,"application/json", (req,res) -> {
            int id = Integer.parseInt(req.params("id"));

            Course course = courseDao.findById(id);
            return course;
        } ,gson::toJson);

        after((req,res) -> {
            res.type("application/json");
        });
    }


}
