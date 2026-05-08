package com.example.myapplication;

import java.time.Instant;

public class Transaction {

    private TransactionType transactionType;
    private Instant date;
    private int amount;

    public Transaction(TransactionType transactionType, Instant date, int amount) {
        setDate(date);
        setTransactionType(transactionType);
        setAmount(amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionType getTraancectionsType() {
        return transactionType;
    }

    public Instant getDate() {
        return date;
    }

    @Override
    public String toString() {
        return transactionType + " " + date.toString() + " " + String.valueOf(amount);
    }


}
