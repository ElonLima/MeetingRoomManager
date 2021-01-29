package com.meetingRoomManager.backend.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {

    private Date timestamp;
    private String messsage;
    private String details;

    public ErrorDetails(Date timestamp, String messsage, String details) {
        super();
        this.timestamp = timestamp;
        this.messsage = messsage;
        this.details = details;
    }
}
