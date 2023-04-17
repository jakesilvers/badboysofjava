package com.techelevator.dao;

import com.techelevator.model.Record;

import java.util.List;

public interface RecordDao {

    int createRecord(Record r, int playedID, int leagueID);

    boolean updateRecord(int matchID);

    List<Record> getRecordsForUser(int userID);

    Record getRecordByLeagueAndUserID (int leagueID, int userID);





}
