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

    // First, I initiate all the JFrame variables I need to make my GUI work
    // JFrame is the box that will appear
    public JFrame frame;
    // JPanel is a container to group components together
    public JPanel panel;
    // JTextField is an inputable field that can be editied
    public JTextField money;
    // JButton is exactly that, a button
    // It can actually do something if it has a ActionListener
    public JButton calculateWithdraw, calculateDeposit, 
            calculateTransfer, calculateBalance;
    public JRadioButton checking, savings;
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 180;
    public String account = "Checking";
    private double balance;
    Account checkingAccount = new Account("Checking");
    Account savingsAccount = new Account("Savings");
    
    public ATMGUI() {
        super(new BorderLayout());
        // Setting the width and hight of the box
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Calling the createPanel class to create the components in the box
        createPanel();
        // Exiting the program once "X" button on the window is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Adding the JPanel varible to the box
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createPanel() {      
        frame = new JFrame("ATM Machine");
        
        // Setting the JButton variable
        calculateWithdraw = new JButton("Withdraw");
        calculateTransfer = new JButton("Transfer To");
        calculateDeposit = new JButton("Deposit");
        calculateBalance = new JButton("Balance");
        
        // Setting the JTextField variables
        money = new JTextField(20);
        
        // Setting the JRadioButton variables
        checking = new JRadioButton("Checking");
        checking.setActionCommand("Checking");
        checking.setSelected(true);
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
        
        // Group buttons
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
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);

                    if(moneyInteger % 20 == 0)
                    {
                        try {
                            Account currentAccount = null;
                            
                            if(account == "Checking") {
                                currentAccount = checkingAccount;
                            }
                            else if(account == "Savings") {
                                currentAccount = savingsAccount;
                            }
                            
                            balance = currentAccount.Balance();
                            
                            if (balance < moneyInteger) {
                               throw new InsufficientFunds("You do not have enough money to withdraw that amount."); 
                            }
                            else {
                                currentAccount.Withdraw(moneyInteger);
                                JOptionPane.showMessageDialog(null, "Withdraw was successful.");
                            }  
                        }
                        catch (InsufficientFunds ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Value is not an "
                                + "increment of 20, please enter a new value.");
                    }
                }
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
                        
                        balance = fromAccount.Balance();
                        
                        if (balance < moneyInteger) {
                            throw new InsufficientFunds("You do not have enough money to withdraw that amount.");
                        }
                        else {
                            
                            fromAccount.Transfer(moneyInteger, toAccount);
                            JOptionPane.showMessageDialog(null, "Transfer was successful.");
                        }
                        
                    }
                    catch(InsufficientFunds ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    
                }
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
                            
                    if(account == "Checking") {
                        currentAccount = checkingAccount;
                    }
                    else if(account == "Savings") {
                        currentAccount = savingsAccount;
                    }
                    
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
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        calculateWithdraw.setAlignmentX(LEFT_ALIGNMENT);
        calculateTransfer.setAlignmentX(LEFT_ALIGNMENT);
        calculateDeposit.setAlignmentX(RIGHT_ALIGNMENT);
        calculateBalance.setAlignmentX(RIGHT_ALIGNMENT);
        
        // Adding all the JLable and JTextField variables to the box
        panel.add(calculateWithdraw);
        panel.add(calculateTransfer);
        panel.add(calculateDeposit);
        panel.add(calculateBalance);
        panel.add(checking);
        panel.add(savings);
        panel.add(money);
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
