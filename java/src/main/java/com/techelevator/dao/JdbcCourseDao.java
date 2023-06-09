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

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course getCourse(String courseName) {

        String sql = "SELECT * FROM course WHERE course_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseName);
        try {
            if (results.next()) {
                Course c = new Course();
                c = mapRowToCourse(results);
                return c;
            }
        } catch (NullPointerException e) {
            throw new NullPointerException(("Sorry, course does not exist!"));
        }
        return null;
    }

    @Override
    public List<Course> listCourses() {
        List<Course> courseList = new ArrayList<>();

        String sql = "SELECT * FROM course;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Course c = mapRowToCourse(results);
            courseList.add(c);
        }

        return courseList;
    }

    @Override
    public String getCourseNameByLeagueID(int leagueID) {
        String courseName;

        String sql = "SELECT course_name FROM course JOIN league ON course.course_id = league.course_id WHERE " +
                "league_id = ? ;";

        try {
            courseName = jdbcTemplate.queryForObject(sql, String.class, leagueID);
            return courseName;
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to find course name");
        }

    }

    @Override
    public int createCourse(Course c) {
        String sql = "INSERT INTO course (course_name, address, city, state, country) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING course_id;";
        int courseID;
        try {
            courseID = jdbcTemplate.queryForObject(sql, int.class, c.getCourseName(), c.getAddress(), c.getCity(), c.getState(), c.getCountry());
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create course");
        }

        return courseID;


    }

    private Course mapRowToCourse(SqlRowSet rs) {
        Course c = new Course();

        c.setCourseID(rs.getInt("course_id"));
        c.setCourseName(rs.getString("course_name"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setState(rs.getString("state"));
        c.setCountry(rs.getString("country"));


        return c;

    }
}
