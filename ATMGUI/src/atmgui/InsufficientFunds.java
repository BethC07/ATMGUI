/*
 * FileName:InsufficientFunds.java
 * Author: Beth Carmichael
 * Date: 06/14/2018
 * Purpose:  
 */
package atmgui;

public class InsufficientFunds extends Exception{
    String errorMessage;
    
    public InsufficientFunds(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString()
    {
        return errorMessage;
    }
    
}
