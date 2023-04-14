package com.techelevator.model;

public class ScoreCard {

    private int scoreCardID;
    private int matchID;
    private int playerID;
    private int scoreValue;

//    public ScoreCard(int scoreCardID, int matchID, int playerID, int scoreValue) {
//        this.scoreCardID = scoreCardID;
//        this.matchID = matchID;
//        this.playerID = playerID;
//        this.scoreValue = scoreValue;
//    }

    public int getScoreCardID() {
        return scoreCardID;
    }

    public void setScoreCardID(int scoreCardID) {
        this.scoreCardID = scoreCardID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
