package com.techelevator.model;

public class NewLeagueDto {
    private String leagueName;
    private String description;
    private int courseID;
    private int adminID;

    public NewLeagueDto(String leagueName, String description, int courseID, int adminID) {
        this.leagueName = leagueName;
        this.description = description;
        this.courseID = courseID;
        this.adminID = adminID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}
