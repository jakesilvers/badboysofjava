package com.techelevator.controller;

import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.MatchDao;
import com.techelevator.dao.ScoreCardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.League;
import com.techelevator.model.NewScoreCardDto;
import com.techelevator.model.ScoreCard;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class ScoreCardController {

    @Autowired
    private ScoreCardDao scoreCardDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LeagueDao leagueDao;
    @Autowired
    private MatchDao matchDao;

//    @PostMapping("/api/scorecards")
//    public ScoreCard createScoreCard(@RequestBody NewScoreCardDto s, Principal p) {
//        ScoreCard newScoreCard = new ScoreCard();
//         if (getFromPrincipal(p) == s.getPlayerID()){
//             newScoreCard.setPlayerID(s.getPlayerID());
//             newScoreCard.setMatchID(s.getMatchID());
//             newScoreCard.setScoreValue(s.getScoreValue());
//             newScoreCard.setScoreCardID(scoreCardDao.createScoreCard(newScoreCard));
//             return newScoreCard;
//         }
//         return null;
//
//    }

    public int getFromPrincipal(Principal p) {
        int userID = userDao.findIdByUsername(p.getName());
        return userID;
    }

    @GetMapping("/api/scorecards/average")
    public int getAverage(@RequestParam int userID) {
        return scoreCardDao.getAverage(userID);
    }

    @GetMapping("/api/scorecards")
    public List<ScoreCard> showAllScoreCardsForUser(@RequestParam int userID) {

        return scoreCardDao.showAllScoreCardsForUser(userID);
    }



    @GetMapping("/api/scorecards/{scoreCardID}")
    public ScoreCard getScoreCard(@PathVariable int scoreCardID) {
        return scoreCardDao.getScoreCardID(scoreCardID);
    }

    @GetMapping("/match/{matchID}/scorecards")
    public List<ScoreCard> getScoreCardsByMatchID(@PathVariable int matchID) {
        return scoreCardDao.getScoreCardsByMatchID(matchID);

    }

    @RequestMapping(path = "/api/scorecards/{scoreCardID}", method = RequestMethod.PUT)
    public boolean updateScore (@RequestBody ScoreCard s, @PathVariable int scoreCardID, Principal p) {
        int matchID = s.getMatchID();
        int userID = s.getPlayerID();
        int score = s.getScoreValue();

        int leagueID = matchDao.getLeagueIDByMatchID(matchID);
        League l = leagueDao.getLeagueByID(leagueID);

        int adminID = l.getAdminID();

        if (adminID == getFromPrincipal(p)) {

            return scoreCardDao.updateScore(matchID, userID, score);
        }
        return false;
    }

}