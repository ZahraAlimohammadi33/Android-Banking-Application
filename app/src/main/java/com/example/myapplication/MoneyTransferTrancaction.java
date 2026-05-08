package com.example.myapplication;
import java.time.Instant;

public class MoneyTransferTrancaction extends Transaction{

    private String firstName;
    private String lastName;
    private String tracking;
    private String accountNumber;
    private String originAccount;
    private boolean paya;
    private int amount;

    public MoneyTransferTrancaction(TransactionType transactionType, Instant date,int amount ,String firstName, String lastName, String tracking, String accountNumber, String originAccount){
        super(transactionType,date,amount);
        this.accountNumber=accountNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.tracking=tracking;
        this.originAccount=originAccount;
        this.paya=paya;
    }

    public boolean isPaya() {
        return paya;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
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
    public String toString(){
        return getTraancectionsType() + getDate().toString() + "\n" + "to : " + firstName + " " + lastName + " - Account number:" + accountNumber + "\n" + "from:" + originAccount + " \n" + "Trackinf code:" + tracking;
    }

}

