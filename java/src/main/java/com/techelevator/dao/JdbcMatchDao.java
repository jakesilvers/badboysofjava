package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.League;
import com.techelevator.model.Match;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcMatchDao  implements MatchDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ScoreCardDao scoreCardDao;


    @Override
    public List<String> showUsersInMatch(int matchID) {
        List<String> matchPlayers = new ArrayList<>();

        String sql = "SELECT username FROM users JOIN match_player ON users.user_id = match_player.player_id " +
                "WHERE match_id = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        while(results.next()) {
            User u = mapRowToUser(results);
            matchPlayers.add(u.getUsername());
        }
        return matchPlayers;
    }

    @Override
    public int createMatch(Match m) {
        int matchID;

        String sql = "INSERT INTO match (league_id, start_time, is_completed) " +
                "VALUES (?, ?, ?) RETURNING match_id;";
        try{
            matchID = jdbcTemplate.queryForObject(sql, int.class, m.getLeagueID(), m.getStartTime(), m.isCompleted() );
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create match");
        }

        return matchID;
    }

    @Override
    public boolean deleteMatch(int matchID) {
        return false;
    }


    @Override
    public boolean endMatch(int matchID) {
        String sql = "UPDATE match SET is_completed = ? WHERE match_id = ?;";

        return jdbcTemplate.update(sql, true, matchID) == 1;
    }


    public Match mapRowToMatch (SqlRowSet rs) {
        Match m = new Match();

        m.setMatchID(rs.getInt("match_id"));
        m.setLeagueID(rs.getInt("league_id"));
        m.setStartTime(rs.getString("start_time"));
        m.setCompleted(rs.getBoolean("is_completed"));

        return m;

    }

    @Override
    public boolean addUserToMatch(int matchID, int userID){
        String sql = "INSERT INTO match_player (match_id, player_id) " +
                "VALUES(?, ?) RETURNING match_id;";

        int success;

        try {
            success = jdbcTemplate.queryForObject(sql, int.class, matchID, userID);
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to add user to match");
        }

        scoreCardDao.createScoreCard(matchID, userID);

        return success > 0;

    }

    @Override
    public Match getMatchByID(int matchID){
        Match m = new Match();

        String sql = "SELECT * FROM match WHERE match_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        if (results.next()) {
            m = mapRowToMatch(results);
            return m;
        }

        return null;

    }

    @Override
    public List<List<String>> getMatchesByLeagueID(int leagueID) {
        List<List<String>> matchList = new ArrayList<>();

        String sql = "SELECT username, user_id, match.match_id, league_id, start_time, is_completed FROM users " +
                "JOIN match_player ON users.user_id = match_player.player_id JOIN match " +
                "ON match_player.match_id = match.match_id WHERE league_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueID);

        while(results.next()) {
            List<String> specificMatch = new ArrayList<>();
            specificMatch.add(results.getString("username"));
            specificMatch.add(results.getString("user_id"));
            specificMatch.add(results.getString("match_id"));
            specificMatch.add(results.getString("league_id"));
            specificMatch.add(results.getString("start_time"));
            specificMatch.add(results.getString("is_completed"));
            matchList.add(specificMatch);

        }

        return matchList;

    }

    @Override
    public int getLeagueIDByMatchID(int matchID) {
        String sql = "SELECT league_id FROM match WHERE match_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        if (results.next()) {
            return results.getInt("league_id");
        }

        return 0;

    }

    @Override
    public League getLeagueByMatchID(int matchID) {
        String sql = "SELECT league_id, league_name FROM league JOIN league.league_id ON match.league_id WHERE match_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        if (results.next()) {
            League l = new League();
            l.setLeagueID(results.getInt("league_id"));
            l.setLeagueName(results.getString("league_name"));
            return l;
        }
        return null;
    }

    @Override
    public String getCourseByMatchID(int matchID) {
        String sql = "SELECT course_name FROM course JOIN league ON course.course_id " +
                "= league.course_id JOIN match ON league.league_id = match.league_id " +
                "WHERE match_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);

        if (results.next()) {
            return results.getString("course_name");
        }

        return null;

    }

    @Override
    public List<List<String>> getMatchesByUserID(int userID) {
        List<List<String>> matchList = new ArrayList<>();

        String sql = "SELECT course_id, league_name, start_time, match.match_id FROM league " +
                "JOIN match ON league.league_id = match.league_id " +
                "JOIN match_player ON match.match_id = match_player.match_id WHERE player_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);

        while (results.next()) {
            List<String> specificMatch = new ArrayList<>();
            specificMatch.add(results.getString("course_id"));
            specificMatch.add(results.getString("league_name"));
            specificMatch.add(results.getString("start_time"));
            specificMatch.add(results.getString("match_id"));
            matchList.add(specificMatch);
        }




        return matchList;
    }










    private User mapRowToUser(SqlRowSet rs) {
        User u = new User();
//        u.setId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
//        u.setPassword(rs.getString("password"));
        //       u.setActivated(true);

        return u;
    }


}
