package com.techelevator.controller;
import com.techelevator.dao.InvitationDao;
import com.techelevator.model.Invitation;
import com.techelevator.model.NewInvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.techelevator.model.Invitation.INVITATION_STATUS_PENDING;


@RestController
@CrossOrigin
public class InvitationController {

    private InvitationDao invitationDao;

    public InvitationController(InvitationDao invitationDao){
        this.invitationDao = invitationDao;
    }

@RequestMapping(path = "/api/invitations", method = RequestMethod.POST)
    public Invitation sendInvitation (@RequestBody NewInvitationDto newInvitationDto){
        Invitation invitation = new Invitation();
        invitation.setLeagueID(newInvitationDto.getLeagueID());
        invitation.setPlayerID(newInvitationDto.getPlayerID());
        invitation.setInvitationStatus(INVITATION_STATUS_PENDING);
        invitation.setInvitationID(invitationDao.sendInvitation(invitation));

        return invitation;

}
@RequestMapping(path = "/api/invitations/{id}" , method = RequestMethod.PUT)
    public Invitation acceptInvitation(@RequestBody Invitation i, @PathVariable int id){

        invitationDao.acceptInvitation(i, id);

        return i;
}

//    @RequestMapping(path = "/api/invitations/{id}" , method = RequestMethod.PUT)
//    public Invitation rejectInvitation(@RequestBody Invitation i, @PathVariable int id){
//
//        invitationDao.rejectInvitation(i, id);
//
//        return i;
//    }

}
