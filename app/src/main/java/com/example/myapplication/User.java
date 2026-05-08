package com.example.myapplication;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private String accountNumber;
    private String cardPassword;
    private int accountCharge;
    private boolean cashFund;
    private String username;
    private String phoneNumber;
    private String password;
    private boolean contactsFeature;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ArrayList<String> recent = new ArrayList<>();

    private ArrayList<Fund> funds = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    private ArrayList<Request> requests=new ArrayList<>();

    public User(String firstName, String lastName, String phoneNumber, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        this.username = username;
        setPassword(password);
        setAccountCharg(0);
//        setCreditCard("22");
//        setCardPassword("1234");
        setContactsFeature(true);
        setCashFund(false);
        setAccountNumber("5022689535");
    }

    public int getAccountCharge() {
        return accountCharge;
    }

    public boolean isCashFund() {
        return cashFund;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountCharg(int accountCharg) {
        this.accountCharge = accountCharg;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Fund> getFunds() {
        return funds;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCashFund(boolean cashFund) {
        this.cashFund = cashFund;
    }

    public void setContactsFeature(boolean contactsFeature) {
        this.contactsFeature = contactsFeature;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<String> getRecent() {
        return recent;
    }

    public void transfer(User user,int amount,String input) {;

        if (!Options.checkMaximumAmount(Options.getFees(input, amount), amount)) {
            Transfer.getAmount().setError("The amount is more than the allowed limit.");
            return;
        }
                if (accountCharge - amount - Options.getFees(input, amount) < 0) {
                    Transfer.getAmount().setError("The account balance is insufficient. backing to menu . ." + "\n");
                    return;
                } else {
                    if (Options.getFees(input, amount) == 2000) {
                        MoneyTransferTrancaction tra = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            tra = new MoneyTransferTrancaction(TransactionType.MoneyTransfer, Calender.now(),amount, user.getFirstName(), user.getLastName(), "3454335", user.getAccountNumber(), this.accountNumber);
                        }
                        Administrator.addPayas(tra, user);
                        Transfer.setTransfered(true);
                        return;
                    } else {
                        setAccountCharg(this.accountCharge - amount - Options.getFees(input, amount));
                        user.setAccountCharg(user.getAccountCharge() + amount);
                        MoneyTransferTrancaction tra = null;
                        ///gerdadanbeCalender
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            tra = new MoneyTransferTrancaction(TransactionType.MoneyTransfer, Calender.now(),amount, user.getFirstName(), user.getLastName(), "3454335", user.getAccountNumber(), this.accountNumber);
                        }
                        transactions.add(tra);
                        recent.add(user.getAccountNumber());
                        user.getTransactions().add(tra);
                        Transfer.setTransfered(true);
                        //cashfund
                        if (isCashFund()) {
                            for(int i=0; i< funds.size(); i ++ ){
                                if(funds.get(i).getKind().equals("CashBalanceFund")){
                                    funds.get(i).addAmount(accountCharge - amount - Options.getFees(input, amount), accountCharge);
                                }
                            }
                        }
                        tra.toString();

                    }
                }

            }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public CashBalanceFund getCashFund() {
        for (Fund fund : funds) {
            if (fund.toString().contains("CashBalanceFund")) {
                return (CashBalanceFund) fund;
            }
        }
        return null;
    }

    public void chargeTheAccount(int number) {
        setAccountCharg(number + getAccountCharge());
        Transaction transaction = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            transaction = new Transaction(TransactionType.Charge, Calender.now(), number);
        }
        transactions.add(transaction);
    }
}

