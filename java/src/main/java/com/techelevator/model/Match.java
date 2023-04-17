package com.techelevator.model;

import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalDateTime;

public class Match {

    private int matchID;
    private int leagueID;
    private String startTime;
    private boolean isCompleted;

//    public Match(int matchID, int leagueID, LocalDateTime startTime, LocalDateTime endTime, boolean isCompleted) {
//        this.matchID = matchID;
//        this.leagueID = leagueID;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.isCompleted = isCompleted;
//    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
