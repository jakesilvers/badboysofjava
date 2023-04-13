package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchDao {

    List<String> showUsersInMatch(int matchID);

    int createMatch(Match m);

    boolean deleteMatch(int matchID);

    boolean endMatch(int matchID);

    boolean addUserToMatch(int matchID, int userID);


}
