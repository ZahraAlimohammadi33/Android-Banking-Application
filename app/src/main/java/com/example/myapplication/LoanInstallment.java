package com.example.myapplication;

import java.time.Duration;
import java.time.Instant;

public class LoanInstallment {

    private boolean Paid;
    private int monthNumber;

    public LoanInstallment(int month, boolean paid){
        this.monthNumber=month;
        this.Paid=paid;
    }

    public static boolean checkPayment(Instant start, int monthNumber){
        Duration duration = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            duration = Duration.between(start, Calender.now());
        }
        long hoursDifference = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hoursDifference = duration.toHours();
        }
        return hoursDifference / 30 > monthNumber;
    }

    public int getMonth() {
        return monthNumber;
    }

    public boolean isPaid() {
        return Paid;
    }
}
