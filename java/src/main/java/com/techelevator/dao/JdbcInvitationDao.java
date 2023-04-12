package com.techelevator.dao;

import com.techelevator.model.Invitation;
import com.techelevator.model.League;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import static com.techelevator.model.Invitation.*;

@Component
public class JdbcInvitationDao implements InvitationDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcInvitationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int sendInvitation(Invitation i) {

        int invitationID;

        String sql= "INSERT INTO invitations (league_id, player_id, invitation_status) " +
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
    public void acceptInvitation(Invitation i, int id) {
        String sql = "UPDATE invitations SET invitation_status = ? WHERE invitation_id = ?;";

        jdbcTemplate.update(sql, INVITATION_STATUS_APPROVED, id);

    }

    @Override
    public void rejectInvitation(Invitation i, int id) {

        String sql = "UPDATE invitations SET invitation_status = ? WHERE invitation_id = ?;";

        jdbcTemplate.update(sql, INVITATION_STATUS_REJECTED, id);

    }

    public Invitation mapRowToInvitation(SqlRowSet rs) {
        Invitation i = new Invitation();

        i.setInvitationID(rs.getInt("invitation_id"));
        i.setLeagueID(rs.getInt("league_id"));
        i.setPlayerID(rs.getInt("player_id"));
        i.setInvitationStatus(rs.getString("invitation_status"));

        return i;
    }
}
