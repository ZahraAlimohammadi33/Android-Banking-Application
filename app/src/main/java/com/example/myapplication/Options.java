package com.example.myapplication;

import java.util.ArrayList;

public class Options {

    private static ArrayList<String> transferMethods=new ArrayList<String>();
    private static ArrayList<String> fundsKind=new ArrayList<String>();

    public static User findUser(String phoneNumber) {
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (Bank.getUsers().get(i).getPhoneNumber().equals(phoneNumber)){
                return Bank.getUsers().get(i);
            }
        }
        return null;
    }

    public static int getFees(String input, int amount) {
        if(input.equals("1")){
            return 300;
        } if(input.equals("2")){
            return 2*amount/100;
        } if(input.equals("3")){
            return 2000;
        }
        return 0;
    }

    public static boolean checkMaximumAmount(int maxAmount, int amount) {
        if (maxAmount == 300 && amount <= 100000) {
            return true;
        } else if (maxAmount == 2000 && amount <= 5000000) {
            return true;
        } else if (maxAmount == 0 && amount <= 8000000) {
            return true;
        } else if (maxAmount == 2 * amount / 100 && amount <= 5000000) {
            return true;
        }
        return false;
    }

    public static void addTraMethods(){
        transferMethods.add("Feri to Feri");
        transferMethods.add("Card to card");
        transferMethods.add("Interbank Bridge");
        transferMethods.add("Stable interbank");
    }

    public static void addFundsKind(){
        fundsKind.add("Saving Fund");
        fundsKind.add("CashBalance Fund");
        fundsKind.add("Bonus Fund");
    }

    public static String findContact(String phone){
        for(int i=0; i<Bank.getUsers().size(); i++){
            if(Bank.getUsers().get(i).getPhoneNumber().equals(phone)){
                return Bank.getUsers().get(i).getAccountNumber();
            }
        }
        return " ";
    }

    public static void getAmount(Fund fund, User user, int amount) {
        if (fund.getKind().equals("BonusFund") && !fund.checkDueDate()) {
            FundDetailActivity.getAmountFund().setError("You cannot deposit or withdraw money to this fund");
            return;
        } else if (fund.getKind().equals("BonusFund") ) {
//            System.out.println("");
            //inja_diolog
        }
        if (fund.getAmount() < amount) {
            FundDetailActivity.getAmountFund().setError("The fund balance is insufficient.");
        } else {
            if (fund.getKind().equals("BonusFund") && fund.checkDueDate()) {
//                fund.setAmount(fund.getAmount() - amount);
//                Administrator.getExpFund().put(user, amount);
            } else {
                fund.setAmount(fund.getAmount() - amount);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Transaction tra=new Transaction(TransactionType.Withdraw,Calender.now(),amount);
                    fund.getTransactions().add(tra);
                }
                user.setAccountCharg(user.getAccountCharge() + amount);
                FundDetailActivity.setDone(true);
            }
        }
    }

    public static void addAmountToFund(Fund fund, User user, int amount) {
        if (fund.toString().contains("BonusFund") && !fund.checkDueDate()) {
            FundDetailActivity.getAmountFund().setError("You cannot deposit or withdraw money to this fund.");
            return;
        }
        if (user.getAccountCharge() < amount) {
            FundDetailActivity.getAmountFund().setError("Your account balance is insufficient. Backing to menu . .");
            return;
        } else {
            fund.setAmount(fund.getAmount() + amount);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Transaction tra=new Transaction(TransactionType.Deposit,Calender.now(),amount);
                fund.getTransactions().add(tra);
            }
            user.setAccountCharg(user.getAccountCharge() - amount);
            FundDetailActivity.setDone(true);
//            System.out.println("Done successfully. Backing to menu . .");
        }
    }

    public static int addFund(String choice, User user) {
        switch (choice) {
            case "0": {
                SavingsFund fund =new SavingsFund(0,"SavingsFund",new ArrayList<Transaction>(),user.getPhoneNumber());
                user.getFunds().add(fund);
                int p = user.getFunds().indexOf(fund);
                return p;
            }
            case "1": {
                CashBalanceFund fund =new CashBalanceFund(0,"CashBalanceFund",new ArrayList<Transaction>(),user.getPhoneNumber());
                user.setCashFund(true);
                user.getFunds().add(fund);
                return user.getFunds().indexOf(fund);
            }
            default: {

        }
    }
    return 0;
    }

    public static int addBounosFund(int amount,String choice, User user, int input){
        if (user.getAccountCharge() < amount) {
            FundActivity.getAmount().setError("Your account balance is insufficient.z");
        } else {
            BonusFund bonusFund = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bonusFund = new BonusFund(amount,"BonusFund",new ArrayList<Transaction>(), user.getPhoneNumber(), input, Calender.now());
            }
            user.getFunds().add(bonusFund);
            user.setAccountCharg(user.getAccountCharge() - amount);
            return user.getFunds().indexOf(bonusFund);
        }
        return 0;
    }


    public static ArrayList<String> getFundsKind() {
        return fundsKind;
    }

    public static ArrayList<String> getTransferMethods() {
        return transferMethods;
    }

    public static User findUserAcc(String accountNumber) {
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (Bank.getUsers().get(i).getAccountNumber().equals(accountNumber)) {
                return Bank.getUsers().get(i);
            }
        }
        return null;
    }
}
