package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchDao {

    List<User> showUsersInMatch(int matchID);

    int createMatch(Match m);

    boolean deleteMatch(int matchID);




}
