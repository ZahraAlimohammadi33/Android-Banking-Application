package com.example.myapplication;

public class Request {

    private String massage;

    private String reply;


    public Request(String massage, String reply) {
        setMassage(massage);
        setReply(reply);
    }



    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


    public String getMassage() {
        return massage;
    }


    public String getReply() {
        return reply;
    }

    @Override
    public String toString() {
        return  massage + "\n" + "Supporter: " + reply;
    }
}
