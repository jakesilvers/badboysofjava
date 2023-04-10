package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    //invite players??

    //void invitePlayer(int userID);

    boolean removePlayer(Principal principal, int userID);

    void invitePlayer(Principal principal, int userID);

    boolean playedAdded();

    League getLeague(Principal principal, int leagueID);

    List<User> getUsersForLeague(Principal principal, int leagueID);

    int createLeague(League l);


}
