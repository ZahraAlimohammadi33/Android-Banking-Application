package com.example.myapplication;

public class FundThread extends Thread{
    @Override
    public void run() {
        Administrator.insertDeposit();
    }
}
