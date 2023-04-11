package com.techelevator.model;

public class League {

    private int leagueID;
    private String leagueName;
    private String description;
    private int courseID;
    private int adminID;

//    public League(int leagueID, String leagueName, String description, int courseID, int adminID) {
//        this.leagueID = leagueID;
//        this.leagueName = leagueName;
//        this.description = description;
//        this.courseID = courseID;
//        this. adminID = adminID;
//    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
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
