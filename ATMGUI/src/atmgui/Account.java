/*
 * FileName:Account.java
 * Author: Beth Carmichael
 * Date: 06/14/2018
 * Purpose:  
 */
package atmgui;

public class Account {
    String account = "";
    double savingsAccount = 8000;
    double checkingAccount = 2000;
    double accountBalance = 0;
    int withdrawsMade = 0;
    
    public Account(String accountName) {
        this.account = accountName;
    }
    
    public double Withdraw(String accountName, double withdraw) {
        if(account == "Savings") {
            accountBalance = savingsAccount - withdraw;
            withdrawsMade++;
        }
        else if(account == "Checking") {
            accountBalance = checkingAccount - withdraw;
            withdrawsMade++;
        }
        return accountBalance;
    }
    
    public double Deposit(String accountName, double deposit) {
        if(account == "Savings") {
            accountBalance = savingsAccount + deposit;
        }
        else if(account == "Checking") {
            accountBalance = checkingAccount + deposit;
        }
        return accountBalance;
    }
    
    public void Transfer(String accountName, double transferFund) {
        if(account == "Savings") {
            savingsAccount = savingsAccount - transferFund;
            checkingAccount = checkingAccount + transferFund;
        }
        else if(this.account == "Checking") {
            checkingAccount = checkingAccount - transferFund;
            savingsAccount = savingsAccount + transferFund;
        }
    }
    
    public double Balance(String accountName) {
        if(account == "Savings") {
            accountBalance = savingsAccount;
        }
        else if(account == "Checking") {
            accountBalance = checkingAccount;
        }
        return accountBalance;
    }
    
    public void serviceCharge(float withdraw) {
        if(withdrawsMade > 4) {
            withdraw += 1.50;
        }
    }
}
