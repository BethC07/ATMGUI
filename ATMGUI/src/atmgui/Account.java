/*
 * FileName:Account.java
 * Author: Beth Carmichael
 * Date: 06/14/2018
 * Purpose:  
 */
package atmgui;

public class Account {
    String account = "";
    double accountBalance = 0;
    int withdrawsMade = 0;
    
    public Account(String accountName) {
        this.account = accountName;
        
        if(this.account == "Savings") {
            this.accountBalance = 8000;
        }
        else {
            this.accountBalance = 2000;
        }
    }
    
    public void Withdraw(double withdraw) {
        if(withdrawsMade > 4) {
            withdraw += 1.50;
        }
        else {
            withdrawsMade++;
        }
        accountBalance = accountBalance - withdraw;
    }
    
    public void Deposit(double deposit) {
        accountBalance = accountBalance + deposit;
    }
    
    public void Transfer(double transferFund, Account toAccount) {
        accountBalance = accountBalance - transferFund;
        toAccount.accountBalance = toAccount.accountBalance + transferFund;
    }
    
    public double Balance() {
        return accountBalance;
    }
}
