package com.techelevator.dao;

import com.techelevator.model.ScoreCard;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface ScoreCardDao {

    int createScoreCard(int matchID, int userID);

    int getAverage(int userId);

    List<ScoreCard> showAllScoreCardsForUser(int userID);

    List<ScoreCard> getScoreCardsByMatchID(int matchID);

    boolean updateScore(int matchID, int userID, int score);

    ScoreCard getScoreCardID(int scoreCardID);

    int getScore(int playerID, int matchID);

    ScoreCard getSpecificScoreCardForMatchAndUser(int userID, int matchID);

    ScoreCard getTheOtherScoreCardForMatchAndUser(int userID, int matchID);


}
