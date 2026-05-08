package com.example.myapplication;



import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class BonusFund extends Fund{

    private int amount;
    private static int time;
    private static Instant start;

    private String kind;

    private String phone;

    private ArrayList<Transaction> transactions=new ArrayList<Transaction>();

    public BonusFund(int amount,String kind, ArrayList<Transaction> transactionsint, String phone, int time, Instant start) {
        super(amount,kind,transactionsint,phone);
        this.time =time;
        this.start =start;
    }

    @Override
    public boolean checkDueDate(){
        Duration duration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            duration = Duration.between(start, Calender.now());
        }
        long hoursDifference = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hoursDifference = duration.toHours();
        }
        return hoursDifference / 24 > time;
    }

    @Override
    public String toString(){
        return "BonusFund  " + "Fund balance:" + amount + "\n" +"Time remaining until fund expiration: " +timePassed();
    }

    public static long timePassed(){
        Duration duration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            duration = Duration.between(start, Calender.now());
        }
        long hoursDifference = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hoursDifference = duration.toHours();
        }
        if(time-hoursDifference/24>0){
            return time-hoursDifference/24;
        } else {
            return 0;
        }
    }

    public static boolean checkInsertDate(){
        Duration duration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            duration = Duration.between(start, Calender.now());
        }
        long hoursDifference = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hoursDifference = duration.toHours();
        }
        return (hoursDifference / 24) % 30 >= 0;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
