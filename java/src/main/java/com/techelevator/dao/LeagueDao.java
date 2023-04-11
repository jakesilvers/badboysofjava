package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    //invite players??

    //void invitePlayer(int userID);

    boolean removePlayer(int leagueID, int userID);

    void invitePlayer(int leagueID, int userID);

    boolean playedAdded();

    List<League> getLeaguesByUserID(int userID);

    List<String> getUsersForLeague(int leagueID);

    int createLeague(League l);

    League getLeagueByID(int leagueID);


}
