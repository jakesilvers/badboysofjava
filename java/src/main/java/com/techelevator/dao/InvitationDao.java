package com.techelevator.dao;

import com.techelevator.model.Invitation;
import com.techelevator.model.League;

public interface InvitationDao {

    int sendInvitation(Invitation i);

    void acceptInvitation(Invitation i, int id);

    void rejectInvitation(Invitation i, int id);

}
