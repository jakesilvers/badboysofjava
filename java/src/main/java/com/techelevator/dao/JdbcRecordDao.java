package com.techelevator.dao;

import com.techelevator.model.Record;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JdbcRecordDao implements RecordDao{

    private JdbcTemplate jdbcTemplate;

    private JdbcRecordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createRecord(int playerID, int leagueID) {
        String sql = "INSERT INTO record (player_id, league_id, win, loss) " +
                "VALUES(?, ?, ?, ?) RETURNING record_id;";
        int recordID;

        try  {
            recordID = jdbcTemplate.queryForObject(sql, int.class, playerID, leagueID, 0, 0);
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to create Record");
        }
        return recordID;
    }

    @Override
    public boolean updateRecord(int matchID, int playerID1, int playerID2) {








        return false;
    }

    @Override
    public List<Record> getRecordsForUser(int userID) {
        return null;
    }

    @Override
    public Record getRecordByLeagueAndUserID(int leagueID, int userID) {
        return null;
    }
}
