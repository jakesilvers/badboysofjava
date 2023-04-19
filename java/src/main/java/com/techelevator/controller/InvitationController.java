package com.techelevator.controller;

import com.techelevator.dao.InvitationDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Invitation;
import com.techelevator.model.League;
import com.techelevator.model.NewInvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.techelevator.model.Invitation.INVITATION_STATUS_PENDING;


@RestController
@CrossOrigin
public class InvitationController {

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeagueDao leagueDao;



    public int getFromPrincipal(Principal p) {
        ;
        int userID = userDao.findIdByUsername(p.getName());
        return userID;
    }

    @RequestMapping(path = "/api/invitations", method = RequestMethod.POST)
    public Invitation sendInvitation(@RequestBody NewInvitationDto newInvitationDto, Principal p) {

        int leagueID = newInvitationDto.getLeagueID();
        League l = leagueDao.getLeagueByID(leagueID);

        int adminID = l.getAdminID();

        if (getFromPrincipal(p) == adminID) {
            Invitation invitation = new Invitation();
            invitation.setLeagueID(newInvitationDto.getLeagueID());
            invitation.setPlayerID(newInvitationDto.getPlayerID());
            invitation.setInvitationStatus(INVITATION_STATUS_PENDING);
            invitation.setInvitationID(invitationDao.sendInvitation(invitation));

            return invitation;
        }
        return null;

    }

    @RequestMapping(path = "/api/invitations/{invitationID}", method = RequestMethod.PUT)
    public Invitation respondToInvitation(@RequestBody Invitation i, @PathVariable int invitationID, Principal p) {
        if (i.getPlayerID() == getFromPrincipal(p)) {
            invitationDao.respondToInvitation(i, invitationID);
            return i;
        }
        return null;
    }

    @GetMapping("/api/invitations/{id}")
    public List<Invitation> listInvitationsByUserID(@PathVariable int id, Principal p){
        if (getFromPrincipal(p) == id) {
            return invitationDao.listInvitationsByUserID(id);
        }
        return null;
    }


    @GetMapping("/api/invitations/{id}/admin")
    public String getUserNameOfAdminOfLeague(@PathVariable int id) {
        return invitationDao.getUserNameOfAdminOfLeague(id);

    }

//    @RequestMapping(path = "/api/invitations/{id}" , method = RequestMethod.PUT)
//    public Invitation rejectInvitation(@RequestBody Invitation i, @PathVariable int id){
//
//        invitationDao.rejectInvitation(i, id);
//
//        return i;
//    }

}
