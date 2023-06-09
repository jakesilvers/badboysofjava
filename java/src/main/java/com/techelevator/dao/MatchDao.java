package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.League;
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

    Match getMatchByID(int matchID);

    List<Match> getMatchesByLeagueID(int leagueID);

    int getLeagueIDByMatchID(int matchID);

    League getLeagueByMatchID(int matchID);

    String getCourseByMatchID(int matchID);

    List<List<String>> getMatchesByUserID(int userIO);




}
