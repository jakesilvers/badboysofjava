package com.techelevator.model;

import java.time.LocalDateTime;

public class NewMatchDto {
    private int leagueID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isCompleted;

    NewMatchDto(int leagueID, LocalDateTime startTime, LocalDateTime endTime, boolean isCompleted){

    }


}
