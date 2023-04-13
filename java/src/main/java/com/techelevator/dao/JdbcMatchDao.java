package com.techelevator.dao;

import com.techelevator.model.Match;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcMatchDao  implements MatchDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<String> showUsersInMatch(int matchID) {
        List<String> matchPlayers = new ArrayList<>();

        String sql = "SELECT username FROM users JOIN match_player ON user.user_id = match_player.player_id " +
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

        String sql = "INSERT INTO match (league_id, start_time, is_completed " +
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
        m.setStartTime(rs.getTime("start_time"));
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

    private User mapRowToUser(SqlRowSet rs) {
        User u = new User();
//        u.setId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
//        u.setPassword(rs.getString("password"));
        //       u.setActivated(true);

        return u;
    }


}
