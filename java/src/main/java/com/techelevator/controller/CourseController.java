package com.techelevator.controller;


import com.techelevator.dao.CourseDao;
import com.techelevator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class CourseController {


    private CourseDao courseDao;

    public CourseController(CourseDao courseDao){
        this.courseDao = courseDao;
    }


    @GetMapping("/api/{courseName}")
    public Course getCourse (@PathVariable String courseName){

        if (courseName.contains(" ")){
            courseName.replace(" ", "%20");
        }

        return courseDao.getCourse(courseName);
    }
    @GetMapping("/api/courses")
    public List<Course>listCourses(){
        return courseDao.listCourses();
    }

    @GetMapping("/api/course/league/{id}")
    public String getCourseNameByLeagueID(@PathVariable int id) {
        return courseDao.getCourseNameByLeagueID(id);
    }

}
