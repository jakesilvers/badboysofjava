package com.techelevator.model;

public class Invitation {
    private int invitationID;
    private int leagueID;
    private int playerID;
    private String invitationStatus;
    public static final String TRANSFER_STATUS_PENDING = "Pending";
    public static final String TRANSFER_STATUS_APPROVED = "Approved";
    public static final String TRANSFER_STATUS_REJECTED = "Rejected";

    public int getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(int invitationID) {
        this.invitationID = invitationID;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public boolean isApproved() {
        return invitationStatus.equalsIgnoreCase(TRANSFER_STATUS_APPROVED);
    }

    public boolean isRejected() {

        return invitationStatus.equalsIgnoreCase(TRANSFER_STATUS_REJECTED);
    }

    public boolean isPending() {

        return invitationStatus.equalsIgnoreCase(TRANSFER_STATUS_PENDING);
    }

}
