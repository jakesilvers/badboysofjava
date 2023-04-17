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
    public int createRecord(Record r, int playerID, int leagueID) {
        String sql = "INSERT INTO record (played_id, league_id, win, loss) " +
                "VALUES(?, ?, ?, ?) RETURNING record_id;";
        int recordID;




        return 0;
    }

    @Override
    public boolean updateRecord(int matchID) {
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
