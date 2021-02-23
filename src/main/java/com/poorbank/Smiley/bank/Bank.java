package com.poorbank.Smiley.bank;

import java.util.HashMap;

public abstract class Bank {
    private double balance;
    private HashMap<String, Double> books = new HashMap<String, Double>();
    private String bankName;
    private static int nextAccountNumber = 100;
    private int accountNumber;

    public Bank(double aBalance, String aBankName){
        balance = aBalance;
        books.put("Deposit", aBalance); // Initial deposit of money into the acccount upon creation of account
        bankName = aBankName;
        accountNumber = nextAccountNumber;
        nextAccountNumber++; // update the variable for the next account
        System.out.println("Your account number is #" + accountNumber + " with a starting balance of $" + balance);
    }

    // Each bank account type must implement a way to remove money from account within the transaction limit rules
    public abstract void withdrawal(double amount);

    // Method to add money to the bank account and update the quick balance amount.
    public void deposit(double amount){
        balance += amount;
        //books.put("Deposit", amount);
        updateBooks("Deposit", amount);
    }

    public void updateBooks(String action, double amount){
        books.put(action, amount);
    }

    // Method to get the account balance
    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        balance = amount;
    }

    // Method to get the account number
    public int getAccountNumber(){
        return accountNumber;
    }

    public String getBankName(){return bankName;}
}
