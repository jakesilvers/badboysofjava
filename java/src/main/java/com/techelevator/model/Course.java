package com.techelevator.model;

public class Course {
    private int courseID;
    private String courseName;
    private String address;
    private String city;
    private String state;
    private String country;

//    public Course(int courseID, String courseName,
//                  String address, String city, String state, String country) {
//        this.courseID = courseID;
//        this.courseName = courseName;
//        this.locationLat = locationLat;
//        this.locationLong = locationLong;
//        this.address = address;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//
//    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
