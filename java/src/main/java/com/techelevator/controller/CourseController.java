package com.techelevator.controller;


import com.techelevator.dao.CourseDao;
import com.techelevator.model.Course;
import com.techelevator.model.NewCourseDto;
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

    @RequestMapping(path = "/api/course", method = RequestMethod.POST)
    public Course createCourse(@RequestBody NewCourseDto dto) {
        Course c = new Course();
        c.setCourseName(dto.getCourseName());
        c.setAddress(dto.getAddress());
        c.setCity(dto.getCity());
        c.setState(dto.getState());
        c.setCountry(dto.getCountry());
        c.setCourseID(courseDao.createCourse(c));

        return c;

    }

}
