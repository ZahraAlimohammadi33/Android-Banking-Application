package com.example.myapplication;

import androidx.annotation.NonNull;

import java.time.Instant;
import java.util.ArrayList;

public class Loan {

    private int amount;
    private int time;

    private String loanKind;
    private LoanCondition condition;
    private Instant start;

    private  ArrayList<LoanInstallment> installments =new ArrayList<>();


    public Loan(int amount, int time, Instant start, String loanKind, LoanCondition condition) {
        this.amount = amount;
        this.time=time;
        this.loanKind=loanKind;
        this.condition=condition;
        this.start=start;
    }


    public void addInstallment(){
        int number=amount/time;
        for(int i=0; i<number; i++){
            int month=1;
             LoanInstallment installments=new LoanInstallment(month,false);
             getInstallments().add(installments);
             month++;
        }
    }
    public ArrayList<LoanInstallment> getInstallments() {
        return installments;
    }

    public int getTime() {
        return time;
    }

    public LoanCondition getCondition() {
        return condition;
    }

    public void setCondition(LoanCondition condition) {
        this.condition = condition;
    }

    public Instant getStart() {
        return start;
    }

    @NonNull
    @Override
    public String toString() {
        return "loan amount: " + amount + " " + "Payment period: " + time +"\n" + "Type: " + loanKind + " "+ "Condition: " + condition;
    }
}
