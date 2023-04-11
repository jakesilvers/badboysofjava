package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    //invite players??

    //void invitePlayer(int userID);

    boolean removePlayer(int userID, int leagueID);

    void invitePlayer(int userID);

    boolean playedAdded();

    League getLeague(int leagueID);

//    List<User> getUsersForLeague(int leagueID);

    int createLeague(League l);


}
