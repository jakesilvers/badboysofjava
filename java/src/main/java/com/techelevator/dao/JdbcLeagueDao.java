package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;
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
    public boolean removePlayer(int userID, int leagueID) {
        boolean playerRemoved = false;




        return playerRemoved;
    }

    @Override
    public void invitePlayer(int userID) {

    }

    @Override
    public boolean playedAdded() {
        return false;
    }

    @Override
    public League getLeague(int leagueID) {
        return null;
    }

//    @Override
//    public List<User> getUsersForLeague(int leagueID) {
//        String sql = "SELECT * FROM users JOIN league_player ON user_id = league_player.player_id " +
//                "WHERE league_id = ? ;";
//        return null;
//    }

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
}
