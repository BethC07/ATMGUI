/*
 * FileName: Week4GUI.java
 * Author: Beth Carmichael
 * Date: 06/14/2018
 * Purpose: The ATMGUI main class shows the parts to making a GUI
 */
package atmgui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ATMGUI extends JPanel {

    // Initialize JFrame
    private JFrame frame;
    // Initialize JTextField
    private JTextField money;
    // Initialize JButtons
    private JButton calculateWithdraw, calculateDeposit, 
            calculateTransfer, calculateBalance;
    // Initialize JRadioButtons
    private JRadioButton checking, savings;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 400;
    // Initialize variables used within the ATMGUI methods
    // accounts sets the radio button checking to be selected
    // as soon as the GUI is called
    private String account = "Checking";
    // balance will hold the accounts current balance
    // for checking and viewing purposes
    private double balance;
    // Initializing objects of the Account class for
    // the checkings account and the savings account
    Account checkingAccount = new Account("Checking");
    Account savingsAccount = new Account("Savings");
    
    // The ATMGUI() method will create the GUI showed to the user
    public ATMGUI() {
        super(new BorderLayout());
        // Calling the createPanel class to create the components in the box
        createPanel();
    }
    
    // The createPanel() method creates everything inside the GUI
    // along with all the checks for the accounts
    private void createPanel() {      
        frame = new JFrame("ATM Machine");
        
        // Setting the JButton variable
        calculateWithdraw = new JButton("Withdraw");
        calculateTransfer = new JButton("Transfer To");
        calculateDeposit = new JButton("Deposit");
        calculateBalance = new JButton("Balance");
        
        // Setting the JTextField variables
        money = new JTextField(16);
        
        // Setting the JRadioButton variables
        checking = new JRadioButton("Checking");
        checking.setActionCommand("Checking");
        checking.setSelected(true);
        // Creation of ActionListener for the checking radio button
        // that sets the account string variable to equal checking
        // for determining which account is selected
        checking.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if("Checking".equals(ae.getActionCommand())) {
                    account = "Checking";
                }
            }
        });
        savings = new JRadioButton("Savings");
        savings.setActionCommand("Savings");
        savings.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if("Savings".equals(ae.getActionCommand())) {
                    account = "Savings";
                }
            }
        });
        
        // Group the radio buttons together to prevent
        // both buttons being pressed at once
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(checking);
        radioGroup.add(savings);
        
        
        // calculateWithdraw actionListener
        calculateWithdraw.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                String moneyString;
                double moneyInteger;

                moneyString = money.getText();
                // Calls the checkIfInteger() method to see if text
                // or nothing was entered to catch the error
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);

                    // Checking to see if the money value inputed is divisible
                    // by 20 using a mod to tell if the remainder of the value
                    // divided by 20 is 0, which means it's a interval of 20
                    if(moneyInteger % 20 == 0)
                    {
                        try {
                            // Initialize a null account object to set later
                            Account currentAccount = null;
                            
                            // Setting the currentAccount object to checkings
                            // and/or savngs depending on the account value
                            // from the radio button
                            if(account == "Checking") {
                                currentAccount = checkingAccount;
                            }
                            else if(account == "Savings") {
                                currentAccount = savingsAccount;
                            }
                            
                            // Computing the balalnce to check and see if the user
                            // has enough funds BEFORE making the withdraw
                            balance = currentAccount.Balance();
                            
                            // If the blance is less than the moeny they are trying
                            // to withdraw, it will throw and error from InsufficientFunds
                            // to tell the user they do not have enough money to make the withdraw
                            if (balance < moneyInteger) {
                               throw new InsufficientFunds("You do not have enough money to withdraw that amount."); 
                            }
                            // If enough money is found, make the wtihfraw and
                            // show the user the withdraw was successful
                            else {
                                currentAccount.Withdraw(moneyInteger);
                                JOptionPane.showMessageDialog(null, "Withdraw was successful.");
                            }  
                        }
                        // Catch for InsufficientFunds
                        catch (InsufficientFunds ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                    // Catch for a non increment of 20
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Value is not an "
                                + "increment of 20, please enter a new value.");
                    }
                }
                // Catch for a non integer value
                else
                {
                    JOptionPane.showMessageDialog(null, "Value provided was not "
                            + "an integer, please enter a new value.");
                }

            }
        });
        
        // calculateTransfer actionListener
        calculateTransfer.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                String moneyString;
                double moneyInteger;

                moneyString = money.getText();
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);
                    
                    try {
                        // Create two null Account objects to store
                        // the account selected and the one not selected
                        Account fromAccount = null;
                        Account toAccount = null;
                        
                        if(account == "Savings") {
                        fromAccount = savingsAccount;
                        toAccount = checkingAccount;
                        }
                        else if(account == "Checking") {
                            fromAccount = checkingAccount;
                            toAccount = savingsAccount;
                        }
                        
                        // Check the balalnce before making the transfer
                        balance = fromAccount.Balance();
                        // Throw InsufficientFunds error if balance is less than
                        // the value inputed
                        if (balance < moneyInteger) {
                            throw new InsufficientFunds("You do not have enough money to withdraw that amount.");
                        }
                        // If everything checks out, make the transfer
                        // and tell the user the transfer was completed
                        else {
                            
                            fromAccount.Transfer(moneyInteger, toAccount);
                            JOptionPane.showMessageDialog(null, "Transfer was successful.");
                        }
                        
                    }
                    // Catch for InsufficientFunds
                    catch(InsufficientFunds ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
                // Catch for a non integer value
                else {
                    JOptionPane.showMessageDialog(null, "Value provided was not "
                            + "an integer, please enter a new value.");
                }
            }
        });
        
        // calculateDesposit actionListener
        calculateDeposit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                String moneyString;
                double moneyInteger;

                moneyString = money.getText();
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);
                    
                    Account currentAccount = null;
                    
                    // Insure the correct account is selected
                    if(account == "Checking") {
                        currentAccount = checkingAccount;
                    }
                    else if(account == "Savings") {
                        currentAccount = savingsAccount;
                    }
                    
                    // Deposit the money value to the correct account
                    // and tell the user it was successful
                    currentAccount.Deposit(moneyInteger);
                    JOptionPane.showMessageDialog(null, "Deposit was successful.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Value provided was not "
                            + "an integer, please enter a new value.");
                }
            }
        });
        
        // calculateBalance actionListener
        calculateBalance.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(account == "Checking") {
                balance = checkingAccount.Balance();
                JOptionPane.showMessageDialog(null, "Your balance is " + balance);
            }
                else if(account == "Savings") {
                    balance = savingsAccount.Balance(); 
                    JOptionPane.showMessageDialog(null, "Your balance is " + balance);

                }
            }
        });
        
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JPanel panel1 = new JPanel();
        BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
        panel1.setLayout(layout1);
        panel1.add(calculateWithdraw);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(calculateDeposit);

        JPanel panel2 = new JPanel();
        BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.X_AXIS);
        panel2.setLayout(layout2);
        panel2.add(calculateTransfer);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(calculateBalance);
        
        JPanel panel3 = new JPanel();
        BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.X_AXIS);
        panel3.setLayout(layout3);
        panel3.add(checking);
        panel3.add(savings);
        
        JPanel panel4 = new JPanel();
        BoxLayout layout4 = new BoxLayout(panel4, BoxLayout.X_AXIS);
        panel4.setLayout(layout4);
        panel4.add(money);
        
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        frame.add(panel);
        
        // Exiting the program once "X" button on the window is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the width and hight of the box
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.pack();
        frame.setVisible(true);
    }
    
    public boolean checkIfInteger(String money) {
        try {
            Integer.parseInt(money);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

 public static void main(String[] args) {
     
	 new ATMGUI();

    }   
}
