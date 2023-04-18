package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;

public interface CourseDao {

    Course getCourse(String courseName);

    List<Course> listCourses();

    String getCourseNameByLeagueID(int leagueID);

    int createCourse(Course c);

}
