package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLeagueDao implements LeagueDao {

    private JdbcUserDao jdbcUserDao;
    private JdbcTemplate jdbcTemplate;

    private JdbcLeagueDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = jdbcUserDao;
    }


    @Override
    public boolean removePlayer(int leagueID, int userID) {

        String sql = "DELETE FROM league_player WHERE league_id = ? AND player_id = ?;";


        return jdbcTemplate.update(sql, leagueID, userID) == 1;
    }

    @Override
    public void invitePlayer(int leagueId, int userID) {

    }

    @Override
    public boolean playedAdded() {
        return false;
    }

    @Override
    public List<League> getLeaguesByUserID (int userID) {
        List<League> userLeagueList = new ArrayList<>();
        String sql = "SELECT * FROM league JOIN league_player ON league.league_id = league_player.league_id WHERE player_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userID);

        while (results.next()) {
            League l = mapRowToLeague(results);
            userLeagueList.add(l);
        }
        return userLeagueList;
    }

    @Override
    public List<String> getUsersForLeague(int leagueID) {
        List<String> leaguesListOFUsers = new ArrayList<>();
        String sql = "SELECT username FROM users JOIN league_player ON user_id = league_player.player_id " +
                "WHERE league_id = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueID);

        while(results.next()){
            User u = mapRowToUser(results);
            leaguesListOFUsers.add(u.getUsername());
        }
        return leaguesListOFUsers;

    }

    @Override
    public int createLeague(League l) {

        String sql = "INSERT INTO league (league_name, description, course_id, admin_id) " +
                "VALUES(?, ?, ?, ?) RETURNING league_id;";
        int leagueID;

        try {
            leagueID = jdbcTemplate.queryForObject(sql, int.class, l.getLeagueName(), l.getDescription(),
            l.getCourseID(), l.getAdminID());
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create league");
        }


        return leagueID;
    }

    public League getLeagueByID(int leagueID) {
        League l = new League();
        String sql = "SELECT * FROM league WHERE league_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueID);
        if (results.next()) {
            l = mapRowToLeague(results);
        }
        return l;
    }

    private League mapRowToLeague(SqlRowSet rs) {
        League l = new League();
        l.setLeagueID(rs.getInt("league_id"));
        l.setLeagueName(rs.getString("league_name"));
        l.setDescription(rs.getString("description"));
        l.setCourseID(rs.getInt("course_id"));
        l.setAdminID(rs.getInt("admin_id"));



        return l;
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
