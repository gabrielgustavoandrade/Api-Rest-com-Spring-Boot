package com.erppsicologo.erppsicologo.dto;

public class ErrorCustom {
    private String message;
    private String messageServer;

    public ErrorCustom(String message, String messageServer) {
        this.message = message;
        this.messageServer = messageServer;
    }

    public ErrorCustom() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageServer() {
        return messageServer;
    }

    public void setMessageServer(String messageServer) {
        this.messageServer = messageServer;
    }
}