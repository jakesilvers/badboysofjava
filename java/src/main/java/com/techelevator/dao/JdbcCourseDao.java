package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCourseDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Course getCourse(String courseName) {
        Course c = new Course();
        String sql = "SELECT * FROM course WHERE course_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseName);

        if (results.next()) {
            c = mapRowToCourse(results);
        }
        return c;
    }

    @Override
    public List<Course> listCourses() {
        List<Course> courseList = new ArrayList<>();

        String sql = "SELECT * FROM course";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()){
            Course c = mapRowToCourse(results);
            courseList.add(c);
        }

        return courseList;
    }

    private Course mapRowToCourse(SqlRowSet rs) {
        Course c = new Course();

        c.setCourseID(rs.getInt("course_id"));
        c.setCourseName(rs.getString("course_name"));
        c.setAddress(rs.getString("address" ));
        c.setCity(rs.getString("city"));
        c.setState(rs.getString("state"));
        c.setCountry(rs.getString("country"));

        return c;

    }
}
