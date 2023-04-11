package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.MatchDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.ScoreCardDao;
import com.techelevator.model.League;
import com.techelevator.model.NewLeagueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController

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
        int userID = userDao.findIdByUsername(p.getName());
        return userID;
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
}
