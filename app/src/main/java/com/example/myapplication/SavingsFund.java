package com.example.myapplication;

import java.util.ArrayList;

public class SavingsFund extends Fund {

    private int amount;
    private String kind;
    private String phone;
    private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
    public SavingsFund(int amount, String kind, ArrayList<Transaction> transactions,String phone) {
        super(amount,kind,transactions,phone);
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "SavingsFund  " + "Fund balance: " + amount;
    }
}
