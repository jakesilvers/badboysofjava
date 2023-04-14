package com.techelevator.dao;

import com.techelevator.model.Invitation;
import com.techelevator.model.League;

import java.util.List;

public interface InvitationDao {

    int sendInvitation(Invitation i);

    void respondToInvitation(Invitation i, int id);

    List<Invitation> listInvitationsByUserID(int userID);

    String getUserNameOfAdminOfLeague(int invitationID);


}
