package com.example.myapplication;

import java.util.ArrayList;

public class Fund {

    private  int amount;

    private int image;

    private String kind;

    private String phone;

    private ArrayList<Transaction> transactions=new ArrayList<Transaction>();


    public Fund(int amount, String kind, ArrayList<Transaction> transactions, String phone){
        setAmount(amount);
        setKind(kind);
        setTransactions(transactions);
        setPhone(phone);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getImage() {
        return image;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    void addAmount(int amount, int accountCharg) {
    }

    boolean checkDueDate(){
        return false;
    }


    @Override
    public String toString() {
        return "  " + "Fund balance:" + amount;
    }

}

