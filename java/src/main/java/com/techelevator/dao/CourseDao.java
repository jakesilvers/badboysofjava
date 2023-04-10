package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;

public interface CourseDao {

    Course getCourse(int courseID);

    List<Course> listCourses();

}
