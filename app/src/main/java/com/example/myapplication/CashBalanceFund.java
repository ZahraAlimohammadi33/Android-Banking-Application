package com.example.myapplication;

import java.util.ArrayList;

public class CashBalanceFund extends Fund {

    private int amount;
    private String kind;

    private String phone;

    private ArrayList<Transaction> transactions=new ArrayList<Transaction>();

    public CashBalanceFund(int amount, String kind, ArrayList<Transaction> transactions, String phone) {
        super(amount,kind,transactions,phone);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addAmount(int amount, int accountCharg) {
        int num = 0;
        int pow = 10;
        int cash = amount;
        while (cash > 0) {
            cash /= 10;
            num++;
        }
        num = num * 3 / 4;
        int pow2 = num;
        while (num > 1) {
            pow *= 10;
            num--;
        }
        amount = amount % pow;
        if (accountCharg < amount) {
            return;
        } else {
            int pow3 = (int) Math.pow(10.0, (double) pow2);
            setAmount(pow3 - amount);
        }
    }

    @Override
    public String toString() {
        return "CashBalanceFund  " + "Fund balance: " + amount;
    }

}
