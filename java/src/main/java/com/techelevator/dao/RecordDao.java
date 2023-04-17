package com.techelevator.dao;

import com.techelevator.model.Record;

import java.util.List;

public interface RecordDao {

    int createRecord(int playedID, int leagueID);

    boolean updateRecord(int matchID, int playerID1, int playerID2);

    List<Record> getRecordsForUser(int userID);

    Record getRecordByLeagueAndUserID (int leagueID, int userID);





}
