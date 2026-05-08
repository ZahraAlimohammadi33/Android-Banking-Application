package com.example.myapplication;



import android.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.*;

public class Administrator extends Thread {

    private static String username;
    private static String password;
    private static int monthlyInsert;
    private static int polFee;
    private static int payaFee;
    private static int cardFee;
    private static int feriFee;
    private String name;
    private static Map<User, Integer> expFund = new HashMap<User, Integer>();
    private static Map<MoneyTransferTrancaction, User> payas = new HashMap<MoneyTransferTrancaction, User>();

    public Administrator(String name, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
        setPayaFee(2000);
        setFeriFee(0);
        setCardFee(300);
        setPolFee(2);
        setMonthlyInsert(20000);
        RequestThread requestThread = new RequestThread();
        TransferThread transferThread=new TransferThread();
        FundThread fundThread=new FundThread();


        while (true){
            requestThread.start();
            transferThread.start();
            fundThread.start();

        }

    }


    public static void setCardFee(int cardFee) {
        Administrator.cardFee = cardFee;
    }

    public static void setPayaFee(int payaFee) {
        Administrator.payaFee = payaFee;
    }

    public static void setPolFee(int polFee) {
        Administrator.polFee = polFee;
    }

    public static void setFeriFee(int feriFee) {
        Administrator.feriFee = feriFee;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }


    public static void setUsername(String username) {
        Administrator.username = username;
    }


    public static void setMonthlyInsert(int monthlyInsert) {
        Administrator.monthlyInsert = monthlyInsert;
    }

    public static int getFeriFee() {
        return feriFee;
    }

    public static Map<User, Integer> getExpFund() {
        return expFund;
    }

    public static int getCardFee() {
        return cardFee;
    }

    public static int getPayaFee() {
        return payaFee;
    }

    public static int getMonthlyInsert() {
        return monthlyInsert;
    }

    public static int getPolFee() {
        return polFee;
    }


    public static void addPayas(MoneyTransferTrancaction paya, User user) {
        payas.put(paya, user);
    }

    public static void setPayasTransaction() {
        for (Map.Entry<MoneyTransferTrancaction, User> entry : payas.entrySet()) {
            Options.findUserAcc(entry.getKey().getOriginAccount()).setAccountCharg(Options.findUserAcc(entry.getKey().getOriginAccount()).getAccountCharge() - entry.getKey().getAmount() - 2000);
            entry.getValue().setAccountCharg(entry.getValue().getAccountCharge() + entry.getKey().getAmount());
            Options.findUserAcc(entry.getKey().getOriginAccount()).getTransactions().add(entry.getKey());
            Options.findUserAcc(entry.getKey().getOriginAccount()).getRecent().add(entry.getKey().getAccountNumber());
            if (Options.findUserAcc(entry.getKey().getOriginAccount()).isCashFund()) {
                Options.findUserAcc(entry.getKey().getOriginAccount()).getCashFund().addAmount(Options.findUserAcc(entry.getKey().getOriginAccount()).getAccountCharge() - entry.getKey().getAmount(), Options.findUserAcc(entry.getKey().getOriginAccount()).getAccountCharge());
            }
        }
    }

    public static void insertDeposit() {
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            for (int j = 0; j < Bank.getUsers().get(i).getFunds().size(); j++) {
                if (Bank.getUsers().get(i).getFunds().get(j).toString().contains("BonusFund") && BonusFund.checkInsertDate()) {
                    Bank.getUsers().get(i).setAccountCharg(Bank.getUsers().get(i).getAccountCharge() + Administrator.getMonthlyInsert());
                }
            }
        }
    }

    public static void sendReply(){
        for(int i=0; i< Bank.getUsers().size(); i++) {
            for (int j = 0; j < Bank.getUsers().get(i).getRequests().size(); j++) {
                if (Bank.getUsers().get(i).getRequests().get(j).getReply().isEmpty()){
                    if(RequestActivity.getNumber()<1) {
                        RequestActivity.getReply().setText("Our colleagues will contact you soon.");
                        Bank.getUsers().get(i).getRequests().get(j).setReply("Our colleagues will contact you soon.");
                        RequestActivity.setNumber(1);
                    }else {
                        RequestActivity.getReply().setText(RequestActivity.getReply().getText().toString()+ "\n" + "Our colleagues will contact you soon.");
                    }
                }
            }
        }
    }

    public static void acceptLoan(User user,Loan loan){
        boolean acception=true;
        for (int i=0; i < user.getLoans().size(); i++){
            for(int j=0; j <user.getLoans().get(i).getInstallments().size(); j++){
                if(LoanInstallment.checkPayment(user.getLoans().get(i).getStart(),j+1)){
                    acception=false;
                }
            }
        }
        if(acception){
            loan.setCondition(LoanCondition.Accepted);
        } else {
            loan.setCondition(LoanCondition.Failed);
        }
    }
}

