package com.techelevator.dao;

import com.techelevator.model.ScoreCard;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface ScoreCardDao {

    int createScoreCard(ScoreCard s);

    int getAverage(User u);

    List<ScoreCard> showAllScoreCardsForUser(User u);

    void UpdateScores(Principal principal, User u);



}
