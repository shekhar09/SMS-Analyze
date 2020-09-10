package com.example.smscategory.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sms implements Serializable {

    private String msgDate;
    private String msgBody;
    private String msgAddress;


    public Sms() {
    }

    public Sms(String msgDate, String msgBody, String msgAddress) {
        this.msgDate = msgDate;
        this.msgBody = msgBody;
        this.msgAddress = msgAddress;
    }


    public String getMsgDate() {
        return this.msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public long getDateLong() {
        return Long.parseLong(this.msgDate);
    }

    public String getFormatDate() {
        return getDate(Long.parseLong(this.msgDate));
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getMsgAddress() {
        return msgAddress;
    }

    public void setMsgAddress(String msgAddress) {
        this.msgAddress = msgAddress;
    }

    public String getDay() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getWeek() {
        return "Week:" + new SimpleDateFormat("W").format(new Date(Long.parseLong(this.msgDate))) + " of " + new SimpleDateFormat("MMM").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getMonth() {
        return new SimpleDateFormat("MMM").format(new Date(Long.parseLong(this.msgDate))) + "'" + new SimpleDateFormat("yy").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getTime12() {
        return new SimpleDateFormat("hh:mm a").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getHour() {
        return new SimpleDateFormat("HH").format(new Date(Long.parseLong(this.msgDate)));
    }

    public String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        return new SimpleDateFormat("dd/MMM/yy").format(new Date(milliSeconds));
    }
}