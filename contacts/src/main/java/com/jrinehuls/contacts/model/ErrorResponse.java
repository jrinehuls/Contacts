package com.jrinehuls.contacts.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private List<String> messages;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
        this.timeStamp = LocalDateTime.now();
    }

    public List<String> getMessage() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
