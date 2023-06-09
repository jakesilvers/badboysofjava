package com.techelevator.dao;

import com.techelevator.model.Invitation;
import com.techelevator.model.League;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.techelevator.model.Invitation.*;

@Component
public class JdbcInvitationDao implements InvitationDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcLeagueDao jdbcLeagueDao;
    private JdbcRecordDao jdbcRecordDao;

    public JdbcInvitationDao(JdbcTemplate jdbcTemplate, JdbcLeagueDao jdbcLeagueDao, JdbcRecordDao jdbcRecordDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcLeagueDao = jdbcLeagueDao;
        this.jdbcRecordDao = jdbcRecordDao;
    }


    @Override
    public int sendInvitation(Invitation i) {

        int invitationID;

        String sql = "INSERT INTO invitations (league_id, player_id, invitation_status) " +
                "VALUES (?, ?, ?) RETURNING invitation_id;";

        try {
            invitationID = jdbcTemplate.queryForObject(sql, int.class, i.getLeagueID(), i.getPlayerID(),
                    INVITATION_STATUS_PENDING);
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create invitation");
        }

        return invitationID;
    }

    @Override
    public void respondToInvitation(Invitation i, int id) {
        String sql = "UPDATE invitations SET invitation_status = ? WHERE invitation_id = ?;";
        if (i.getInvitationStatus().equals(INVITATION_STATUS_APPROVED)) {
            jdbcTemplate.update(sql, INVITATION_STATUS_APPROVED, id);
            jdbcLeagueDao.addUserIntoLeaguePlayer(i.getLeagueID(), i.getPlayerID());
            jdbcRecordDao.createRecord(i.getPlayerID(), i.getLeagueID());

        }
        if (i.getInvitationStatus().equals(INVITATION_STATUS_REJECTED)) {
            jdbcTemplate.update(sql, INVITATION_STATUS_REJECTED, id);
        }

    }

//    @Override
//    public void rejectInvitation(Invitation i, int id) {
//
//        String sql = "UPDATE invitations SET invitation_status = ? WHERE invitation_id = ?;";
//
//        jdbcTemplate.update(sql, INVITATION_STATUS_REJECTED, id);
//
//    }

    @Override
    public List<Invitation> listInvitationsByUserID(int userID) {
        List<Invitation> usersInvitations = new ArrayList<>();

        String sql = "SELECT * FROM invitations WHERE player_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);

        while (results.next()) {
            Invitation i = mapRowToInvitation(results);
            usersInvitations.add(i);
        }

        return usersInvitations;

    }

    @Override
    public String getUserNameOfAdminOfLeague(int invitationID) {
        String sql = "SELECT username FROM users JOIN league ON users.user_id = league.admin_id " +
                "JOIN invitations ON league.league_id = invitations.league_id WHERE " +
                "invitation_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, invitationID);

        if (results.next()) {
            String username = mapRowToUsername(results);
            return username;
        }

        return null;


    }

    public Invitation mapRowToInvitation(SqlRowSet rs) {
        Invitation i = new Invitation();

        i.setInvitationID(rs.getInt("invitation_id"));
        i.setLeagueID(rs.getInt("league_id"));
        i.setPlayerID(rs.getInt("player_id"));
        i.setInvitationStatus(rs.getString("invitation_status"));

        return i;
    }

    public String mapRowToUsername(SqlRowSet rs) {
        String username = rs.getString("username");
        return username;
    }
}
