package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.League;
import com.techelevator.model.NewLeagueDto;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class LeagueController {

    private CourseDao courseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeagueDao leagueDao;

    public LeagueController(LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }

    public int getFromPrincipal(Principal p) {
        return userDao.findIdByUsername(p.getName());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/api/league", method = RequestMethod.POST)
    public League createLeague(@Valid @RequestBody NewLeagueDto l, Principal principal) {

        League newLeague = new League();

        newLeague.setLeagueName(l.getLeagueName());
        newLeague.setDescription(l.getDescription());
        newLeague.setCourseID(l.getCourseID());
        newLeague.setAdminID(getFromPrincipal(principal));
        newLeague.setLeagueID(leagueDao.createLeague(newLeague));


        return newLeague;
    }
    @GetMapping("/api/league/user/{id}")
    public List<League> getLeaguesByUserId(@PathVariable int id){
        return leagueDao.getLeaguesByUserID(id);
    }

    @GetMapping("/api/league/{id}/user")
    public List<String> getUsersForLeague(@PathVariable int id) {
        return leagueDao.getUsersForLeague(id);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping ( path ="/api/league/{leagueID}/user/{userID}", method = RequestMethod.DELETE)
    public boolean removePlayer (@PathVariable int leagueID, @PathVariable int userID, Principal p){
        League l = leagueDao.getLeagueByID(leagueID);

        int adminID = l.getAdminID();

        int currentUserID = getFromPrincipal(p);


        if (adminID == currentUserID ) {
            return leagueDao.removePlayer(leagueID, userID);
        }
        return false;
    }

    @GetMapping ("api/league/{leagueID}")
    public League getLeagueByLeagueId (@PathVariable int leagueID) {
        return leagueDao.getLeagueByID(leagueID);
    }



}





