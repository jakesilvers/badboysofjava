package com.techelevator.dao;

import com.techelevator.model.Record;
import com.techelevator.model.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcRecordDao implements RecordDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MatchDao matchDao;


    @Override
    public int createRecord(int playerID, int leagueID) {
        String sql = "INSERT INTO record (player_id, league_id, win, loss) " +
                "VALUES(?, ?, ?, ?) RETURNING record_id;";
        int recordID;

        try {
            recordID = jdbcTemplate.queryForObject(sql, int.class, playerID, leagueID, 0, 0);
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create Record");
        }
        return recordID;
    }

//    @Override
//    public boolean updateRecord(int matchID, int playerID1, int playerID2) {
//        List<ScoreCard> scoreCardList = scoreCardDao.getScoreCardsByMatchID(matchID);
//        int leagueID = matchDao.getLeagueIDByMatchID(matchID);




//            String sql1Wins = "SELECT win FROM record WHERE league_id = ? AND playerID = ?;";
//            SqlRowSet resultsOfWinsPlayer1 = jdbcTemplate.queryForRowSet(sql1Wins, leagueID, playerID1);
//            int player1Wins;
//            if (resultsOfWinsPlayer1.next()) {
//                player1Wins = resultsOfWinsPlayer1.getInt("win");
//            } else {
//                player1Wins = 0;
//            }
//            String sql1Loss = "SELECT loss FROM record WHERE league_id = ? AND playerID = ?;";
//            SqlRowSet resultsOfLossPlayer1 = jdbcTemplate.queryForRowSet(sql1Loss, leagueID, playerID1);
//            int player1Loss;
//            if (resultsOfLossPlayer1.next()) {
//                player1Loss = resultsOfLossPlayer1.getInt("loss");
//            } else {
//                player1Loss = 0;
//            }
//
//
//            String sql2Wins = "SELECT win FROM record WHERE league_id = ? AND playerID = ?;";
//            SqlRowSet resultsOfWinsPlayer2 = jdbcTemplate.queryForRowSet(sql2Wins, leagueID, playerID2);
//            int player2Wins;
//            if (resultsOfWinsPlayer2.next()) {
//                player2Wins = resultsOfWinsPlayer2.getInt("win");
//            } else {
//                player2Wins = 0;
//            }
//            String sql2Loss = "SELECT loss FROM record WHERE league_id = ? AND playerID = ?;";
//            SqlRowSet resultsOfLossPlayer2 = jdbcTemplate.queryForRowSet(sql2Loss, leagueID, playerID2);
//            int player2Loss;
//            if (resultsOfLossPlayer2.next()) {
//                player2Loss = resultsOfLossPlayer2.getInt("loss");
//            } else {
//                player2Loss = 0;
//            }
//
//
//            int player1Score = 0;
//            int player2Score = 0;
//
//            String sql1 = "SELECT score FROM score_card WHERE match_id = ? AND player_id = ?;";
//            SqlRowSet results1 = jdbcTemplate.queryForRowSet(sql1, matchID, playerID1);
//            if (results1.next()) {
//                player1Score = mapRowToScore(results1);
//            }
//
//            String sql2 = "SELECT score FROM score_card WHERE match_id = ? AND player_id = ?;";
//            SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, matchID, playerID2);
//            if (results2.next()) {
//                player2Score = mapRowToScore(results2);
//            }
//
//            if (player1Score < player2Score) {
//                String updatePlayer1Wins = "UPDATE record SET win = ? WHERE player_id = ? AND league_id = ?;";
//                boolean works = jdbcTemplate.update(updatePlayer1Wins, player1Wins + 1, playerID1, leagueID) == 1;
//
//                String updatePlayer2Loss = "UPDATE record SET loss = ? WHERE player_id = ? AND league_id = ?;";
//                boolean works2 = jdbcTemplate.update(updatePlayer2Loss, player2Loss + 1, playerID2, leagueID) == 1;
//                return works && works2;
//            } else {
//                String updatePlayer1Loss = "UPDATE record SET loss = ? WHERE player_id = ? AND league_id = ?;";
//                boolean works = jdbcTemplate.update(updatePlayer1Loss, player1Loss + 1, playerID1, leagueID) == 1;
//
//                String updatePlayer2Win = "UPDATE record SET win = ? WHERE player_id = ? AND league_id = ?;";
//                boolean works2 = jdbcTemplate.update(updatePlayer2Win, player2Wins + 1, playerID2, leagueID) == 1;
//                return works && works2;
//            }

//        return false;
//    }

    @Override
    public List<Record> getRecordsForUser(int userID) {
        return null;
    }

    @Override
    public Record getRecordByLeagueAndUserID(int leagueID, int userID) {
        return null;
    }

    private int mapRowToScore(SqlRowSet rs) {
        int score = rs.getInt("score");
        return score;
    }

    @Override
    public boolean updateWinColumn(int playerID, int matchID) {
        int leagueID = matchDao.getLeagueIDByMatchID(matchID);
        String sql = "SELECT win FROM record WHERE league_id = ? AND player_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueID, playerID);

        int wins = getNumberOfWins(results);

        String sqlUpdate = "UPDATE record SET win = ? WHERE player_id = ? AND league_id = ?;";
        return jdbcTemplate.update(sqlUpdate, wins + 1, playerID, leagueID) == 1;


    }

    private int getNumberOfWins (SqlRowSet rs) {
        int wins;
        if (rs.next()) {
            wins = rs.getInt("win");
        } else {
            wins = 0;
        }
        return wins;
    }

    @Override
    public boolean updateLossColumn(int playerID, int matchID) {
        int leagueID = matchDao.getLeagueIDByMatchID(matchID);
        String sql = "SELECT loss FROM record WHERE league_id = ? AND playerID = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueID, playerID);

        int losses = getNumberOfLosses(results);

        String sqlUpdate = "UPDATE record SET loss = ? WHERE player_id = ? AND league_id = ?;";
        return jdbcTemplate.update(sqlUpdate, losses + 1, playerID, leagueID) == 1;
    }

    private int getNumberOfLosses (SqlRowSet rs) {
        int losses;
        if (rs.next()) {
            losses = rs.getInt("loss");
        } else {
            losses = 0;
        }
        return losses;
    }


}
