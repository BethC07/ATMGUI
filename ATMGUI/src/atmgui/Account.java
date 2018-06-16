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
    
    public void Withdraw(String accountName) {
        this.account = accountName;
    }
    
    public void Deposit(String accountName) {
        this.account = accountName;
        
    }
    
    public void Transfer(String accountName) {
        this.account = accountName;
    }
    
    public void Balance(String accountName) {
        this.account = accountName;
    }
    
    public void serviceCharge() {
        
    }
}
