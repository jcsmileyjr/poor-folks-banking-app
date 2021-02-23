package com.poorbank.Smiley.controllers;

import com.poorbank.Smiley.bank.Bank;
import com.poorbank.Smiley.bank.CheckingAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BankController {

    ArrayList<Bank> accounts = new ArrayList<>();
    Bank currentAccount;

    @GetMapping("index") //route at "/"
    public String landingPage(){
        currentAccount = null; //reset currentAccount
        return "index";
    }

    @GetMapping("openAccount")
    public String initialOptions1(){
        return "CreateAccount";
    }

    @GetMapping("ShowOptions")
    public String accountOptions(){
        return "AccountOptions";
    }

    @GetMapping("ListAccounts")
    public String gotoListAccountPage(Model model){
        model.addAttribute("accounts", accounts);
        return "ListAccounts";
    }

    @GetMapping("AccountNumber")
    public String gotoAccountPage(){
        return "RetrieveAccount";
    }

    @PostMapping("AccountNumber")
    public String retrieveAccount(@RequestParam int accountNumber){
        for(Bank account: accounts) {
            if (account.getAccountNumber() == accountNumber) {
                currentAccount = account;
            }
        }
        return "AccountOptions";
    }

    @GetMapping("Deposit")
    public String gotoDepositPage(){
        return "Deposit";
    }

    @PostMapping("Deposit")
    public String depositAmount(@RequestParam int depositAmount){
        currentAccount.deposit(depositAmount);
        return "AccountOptions";
    }

    @GetMapping("WithDraw")
    public String gotoWithDrawPage(){
        return "Withdraw";
    }

    @PostMapping("WithDraw")
    public String withdrawAmount(@RequestParam int withdrawAmount){
        currentAccount.withdrawal(withdrawAmount);
        return "AccountOptions";
    }

    @PostMapping("createAccount")
    public String createAccount(@RequestParam int depositAmount, String accountName){
        //create account with depositAmount
        CheckingAccount account = new CheckingAccount(depositAmount, accountName);
        accounts.add(account);
        currentAccount = account;
        //model.addAttribute("accountNumber", account.getAccountNumber());
        return "AccountOptions";
    }

    @GetMapping("ShowBalance")
    public String showBalance(Model model){
//        Boolean found = false;
//        for(Bank household: accounts){
//            if(household.getAccountNumber() == account){
//                model.addAttribute("balance", household.getBalance());
//                found = true;
//            }
//        }
//
//        if(!found){
//            model.addAttribute("balance", "no funds");
//        }

        model.addAttribute("balance", currentAccount.getBalance());
        return "ShowBalance";
    }
}
