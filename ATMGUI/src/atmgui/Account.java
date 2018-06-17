/*
 * FileName:Account.java
 * Author: Beth Carmichael
 * Date: 06/14/2018
 * Purpose:  
 */
package atmgui;

public class Account {
    String account = "";
    float savingsAccount = 8000;
    float checkingAccount = 2000;
    float accountBalance = 0;
    int withdrawsMade = 0;
    
    public Account(String accountName) {
        this.account = accountName;
    }
    
    public float Withdraw(String accountName, float withdraw) {
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
    
    public float Deposit(String accountName, float deposit) {
        if(account == "Savings") {
            accountBalance = savingsAccount + deposit;
        }
        else if(account == "Checking") {
            accountBalance = checkingAccount + deposit;
        }
        return accountBalance;
    }
    
    public void Transfer(String accountName, float transferFund) {
        if(account == "Savings") {
            savingsAccount = savingsAccount - transferFund;
            checkingAccount = checkingAccount + transferFund;
        }
        else if(this.account == "Checking") {
            checkingAccount = checkingAccount - transferFund;
            savingsAccount = savingsAccount + transferFund;
        }
    }
    
    public float Balance(String accountName) {
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
