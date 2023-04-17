package com.techelevator.controller;


import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.MatchDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.League;
import com.techelevator.model.Match;
import com.techelevator.model.NewMatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class MatchController {


    private MatchDao matchDao;

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private UserDao userDao;

    public MatchController(MatchDao matchDao) {
        this.matchDao = matchDao;

    }

    public int getFromPrincipal(Principal p) {
        int userID = userDao.findIdByUsername(p.getName());
        return userID;
    }

    @GetMapping("/api/match/{id}/user")
    public List<String> showUsersInMatch(@PathVariable int id) {
        return matchDao.showUsersInMatch(id);
    }

    @GetMapping("/api/match/{id}")
    public Match getMatchByID(@PathVariable int id) {
        return matchDao.getMatchByID(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/api/match", method = RequestMethod.POST)
    public Match createMatch(@Valid @RequestBody NewMatchDto m, Principal p) {
        int leagueID = m.getLeagueID();
        League l = leagueDao.getLeagueByID(leagueID);
        int adminID = l.getAdminID();

        if (adminID == getFromPrincipal(p)) {
            Match newMatch = new Match();
            newMatch.setLeagueID(m.getLeagueID());
            newMatch.setStartTime(m.getStartTime());
            newMatch.setCompleted(m.isCompleted());
            newMatch.setLeagueID(matchDao.createMatch(newMatch));
            return newMatch;
        }

        return null;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "api/match/{matchID}/user/{userID}")
    public boolean addUserToMatch(@PathVariable int matchID, @PathVariable int userID, Principal p) {
        Match m = matchDao.getMatchByID(matchID);
        int leagueID = m.getLeagueID();
        League l = leagueDao.getLeagueByID(leagueID);
        int adminID = l.getAdminID();

        if (adminID == getFromPrincipal(p)) {
            return matchDao.addUserToMatch(matchID, userID);
        }
        return false;


    }

    @RequestMapping(path = "api/match/{matchID}", method = RequestMethod.PUT)
    public boolean endMatch(@PathVariable int matchID, Principal p) {
        Match m = matchDao.getMatchByID(matchID);
        int leagueID = m.getLeagueID();
        League l = leagueDao.getLeagueByID(leagueID);
        int adminID = l.getAdminID();

        if (adminID == getFromPrincipal(p)) {
            return matchDao.endMatch(matchID);
        }
        return false;

    }

    @RequestMapping(path = "api/league/{leagueID}/match", method = RequestMethod.GET)
    public List<Match> getMatchesByLeagueID(@PathVariable int leagueID) {
        return matchDao.getMatchesByLeagueID(leagueID);
    }


}
