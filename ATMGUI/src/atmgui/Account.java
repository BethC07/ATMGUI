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
    
    public Account(String accountName) {
        
    }
    
    public float Withdraw(String accountName, float withdraw) {
        this.account = accountName;
        if(this.account == "Savings") {
            accountBalance = savingsAccount - withdraw;
        }
        else if(this.account == "Checking") {
            accountBalance = checkingAccount - withdraw;
        }
        return accountBalance;
    }
    
    public float Deposit(String accountName, float deposit) {
        this.account = accountName;
        if(this.account == "Savings") {
            accountBalance = savingsAccount + deposit;
        }
        else if(this.account == "Checking") {
            accountBalance = checkingAccount + deposit;
        }
        return accountBalance;
    }
    
    public void Transfer(String accountName, float transferFund) {
        this.account = accountName;
        if(this.account == "Savings") {
            savingsAccount = savingsAccount - transferFund;
            checkingAccount = checkingAccount + transferFund;
        }
        else if(this.account == "Checking") {
            checkingAccount = checkingAccount - transferFund;
            savingsAccount = savingsAccount + transferFund;
        }
    }
    
    public void Balance(String accountName) {
        this.account = accountName;
    }
    
    public void serviceCharge() {
        
    }
}

