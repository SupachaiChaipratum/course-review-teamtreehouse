package com.thehoistory.learn.dao;

import com.thehoistory.learn.model.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oCourseDaoTest {

    private Sql2oCourseDao dao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        dao = new Sql2oCourseDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetId() throws Exception {
        Course course = getNewCourse();
        int originalCourseId = course.getId();

        dao.add(course);

        assertNotEquals(originalCourseId, course.getId());


    }

    @Test
    public void addedCourseAreReturnFromFindAll() throws Exception{
        Course course = getNewCourse();

        dao.add(course);


         assertEquals(1,dao.findAll().size());
    }

    @Test
    public void onCoursesReturnEmptyList() throws  Exception {
        assertEquals(0,dao.findAll().size());
    }


    @Test
    public void existingCoursesCanBeFoundById() throws Exception {
        Course course = getNewCourse();
        dao.add(course);

        Course foundCourse = dao.findById(course.getId());

        assertEquals(course , foundCourse);
    }

    private Course getNewCourse() {
        return new Course("Test","Http://test.com");
    }
}