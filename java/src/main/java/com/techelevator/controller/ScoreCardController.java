package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.League;
import com.techelevator.model.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @Autowired
    private RecordDao recordDao;

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



//        List<String> usersInMatch = matchDao.showUsersInMatch(matchID);
//        String player1 = usersInMatch.get(0);
//        String player2 = usersInMatch.get(1);
//
//        int player1ID = userDao.findIdByUsername(player1);
//        int player2ID = userDao.findIdByUsername(player2);
//
//        int player1Score = scoreCardDao.getScore(player1ID, matchID);
//        int player2Score = scoreCardDao.getScore(player2ID, matchID);
//
//        if (player1Score < player2Score) {
//            recordDao.updateWinColumn(player1ID, matchID);
//            recordDao.updateLossColumn(player2ID, matchID);
//        }
//
//        if (player2Score < player1Score) {
//            recordDao.updateWinColumn(player2ID, matchID);
//            recordDao.updateLossColumn(player1ID, matchID);
//        }


        return false;
    }

}