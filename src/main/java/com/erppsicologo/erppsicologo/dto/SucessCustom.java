package com.erppsicologo.erppsicologo.dto;

public class SucessCustom {

    private String message;
    private String messageServer;

    public SucessCustom(String message, String messageServer) {
        this.message = message;
        this.messageServer = messageServer;
    }

    public SucessCustom() {
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