package com.poorbank.Smiley.bank;

public class CheckingAccount extends Bank {

    public CheckingAccount(double aBalance, String aBankName){
        super(aBalance, aBankName);
    }

    @Override
    public void withdrawal(double amount) {
        double newBalance = getBalance() - amount;
        super.setBalance(newBalance);
        super.updateBooks("Withdrawal", amount);

        // Check if over withdrawn
        if(super.getBalance()<0){
            System.out.println("👿 You have over withdrawn. We have taken your tractor (or someone's tractor) as hostage until you pay us $55 in hard earn American dollars.");
            double withdrawnBalance = getBalance() - 55;
            super.setBalance(withdrawnBalance);
            super.updateBooks("Overdrawn Fees", 55);
            System.out.println("💀 Your new balance is " + getBalance() + ", including the over withdrawal fee! Get out of here.");
        }
    }
}
