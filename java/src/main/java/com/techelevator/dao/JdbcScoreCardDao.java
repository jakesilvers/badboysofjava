package com.techelevator.dao;

import com.techelevator.model.ScoreCard;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcScoreCardDao implements ScoreCardDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcScoreCardDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public int createScoreCard(int matchID, int userID) {
        int scorecardId;

        String sql = "INSERT INTO scorecard (match_id, player_id, score) VALUES (?, ?, ?) RETURNING scoretable_id";
        try {
            scorecardId = jdbcTemplate.queryForObject(sql, int.class, matchID, userID, 0);
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create Scorecard");

        }
        return scorecardId;
    }

    @Override
    public int getAverage(int userId) {
        String sql = "SELECT AVG(score) AS avg_score FROM scorecard JOIN users ON scorecard.player_id = users.user_id WHERE users.user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if (result.next()) {
            return result.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public List<ScoreCard> showAllScoreCardsForUser(int userId) {
        String sql = "SELECT * FROM scorecard JOIN league_player ON scorecard.player_id = players.player_id WHERE players.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<ScoreCard> scoreCards = new ArrayList<>();
        while (results.next()) {
            ScoreCard s = mapRowToScoreCard(results);
            scoreCards.add(s);
        }
        return scoreCards;
    }



    @Override
    public ScoreCard getScoreCardID(int scoreCardID) {
        String sql = "SELECT * FROM scorecard WHERE scoretable_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, scoreCardID);
        if (result.next()) {
            return mapRowToScoreCard(result);
        } else {
            return null;
        }
    }

    public List<ScoreCard> getScoreCardsByMatchID(int matchID) {

        List<ScoreCard> matchScoreCards = new ArrayList<>();
        String sql = "SELECT * FROM scorecard WHERE match_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        while(results.next()) {
            ScoreCard s = mapRowToScoreCard(results);
            matchScoreCards.add(s);
        }
        if (matchScoreCards.size() > 0) {
            return matchScoreCards;
        }
        return null;


    }

    @Override
    public boolean updateScore(int matchID, int userID, int score) {
        String sql = "UPDATE score SET score = ? WHERE match_id = ? AND player_id = ?;";

        return jdbcTemplate.update(sql, score, matchID, userID) == 1;

    }



    private ScoreCard mapRowToScoreCard (SqlRowSet rs) {
        ScoreCard s = new ScoreCard();

        s.setScoreCardID(rs.getInt("scoretable_id"));
        s.setMatchID(rs.getInt("match_id"));
        s.setPlayerID(rs.getInt("player_id"));
        s.setScoreValue(rs.getInt("score"));
        return s;

    }



}
