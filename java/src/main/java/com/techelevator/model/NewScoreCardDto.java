package com.techelevator.model;

public class NewScoreCardDto {
    private int matchID;
    private int playerID;
    private int scoreValue;

    NewScoreCardDto (int matchID, int playerID, int scoreValue) {
        this.matchID = matchID;
        this.playerID = playerID;
        this.scoreValue = scoreValue;
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
