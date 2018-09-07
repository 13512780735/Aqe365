package com.likeit.aqe365.event;

public class PayEventMessage {
    String type;

    public PayEventMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
