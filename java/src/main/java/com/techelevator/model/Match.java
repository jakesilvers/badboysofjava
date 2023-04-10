package com.techelevator.model;

import java.time.LocalDateTime;

public class Match {

    private int matchID;
    private int leagueID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isCompleted;

    public Match(int matchID, int leagueID, LocalDateTime startTime, LocalDateTime endTime, boolean isCompleted) {
        this.matchID = matchID;
        this.leagueID = leagueID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = isCompleted;
    }

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
