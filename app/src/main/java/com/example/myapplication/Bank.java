package com.example.myapplication;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Bank {

    private static ArrayList<User> users = new ArrayList<>();
    public Bank(){
        User user=new User("zahra", "alimohammmadi","09127782618","011053616","Yamahdi313@");
        users.add(user);
        user.getContacts().add(new Contact("","",""));
        user.getRecent().add("22222");
        ArrayList<Transaction> tra=new ArrayList<Transaction>();
        user.getFunds().add(new Fund(200,"kind",tra,user.getPhoneNumber()));
        User user1=new User("zahra", "alimohammmadi","09334535216","0110","Yamahdi110@");
        user1.setAccountNumber("1234");
        users.add(user1);

    }
    public static boolean checkUserLogin(String phoneNumber, String password, ArrayList<User> users) {

        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static boolean checkPassword(String password) {
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{4,}$", password);
    }

//    public static void moneyTransfer(User user) {
//        switch (chois) {
//            case "1": {
//                int num = 1;
//                if (user.getRecent().isEmpty()) {
//                    System.out.println("The list is empty. Backing to menu . . " + "\n");
//                } else {
//                    for (int i = 0; i < user.getRecent().size(); i++) {
//                        System.out.println(num + "_" + user.getRecent().get(i));
//                        num++;
//                    }
//                    System.out.println("Select one:");
//                    int cho = userinput.nextInt();
//                    for (int i = 0; i < Bank.getUsers().size(); i++) {
//                        if (Bank.getUsers().get(i).getAccountNumber().equals(user.getRecent().get(cho - 1))) {
//                            user.transfer(Bank.getUsers().get(i));
//                        } else if (i == Bank.getUsers().size() - 1) {
//                            System.out.print("Cant find thr user");
//                        }
//                    }
//                }
//                moneyTransfer(user);
//                break;
//            }
//            case "2": {
//                help(user);
//                moneyTransfer(user);
//                break;
//            }
//            case "3": {
//                System.out.print("Enter the destination account:");
//                String account = userinput.nextLine();
//                for (int i = 0; i < Bank.getUsers().size(); i++) {
//                    if (Bank.getUsers().get(i).getAccountNumber().equals(account)) {
//                        user.transfer(Bank.getUsers().get(i));
//                    } else if (i == Bank.getUsers().size() - 1) {
//                        System.out.print("Cant find thr user. Backing to menu. . .");
//                    }
//                }
//                moneyTransfer(user);
//                break;
//            }
//            case "4": {
//                menu2(user);
//                break;
//            }
//            default:{
//                moneyTransfer(user);
//            }
//        }
//    }

}
