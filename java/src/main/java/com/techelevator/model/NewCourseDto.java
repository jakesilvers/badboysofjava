package com.techelevator.model;

public class NewCourseDto {
    private String courseName;
    private String courseAddress;
    private String city;
    private String state;
    private String country;

    NewCourseDto(String courseName, String courseAddress, String city, String state, String country){
        this.courseName = courseName;
        this.courseAddress = courseAddress;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
