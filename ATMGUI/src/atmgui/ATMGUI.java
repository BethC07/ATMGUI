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

public class ATMGUI extends JFrame {

    // First, I initiate all the JFrame variables I need to make my GUI work
    // JFrame is the box that will appear
    public JFrame frame;
    // JPanel is a container to group components together
    public JPanel panel;
    // JTextField is an inputable field that can be editied
    public JTextField money;
    // JButton is exactly that, a button
    // It can actually do something if it has a ActionListener
    public JButton calculateWithdraw, calculateDesposit, 
            calculateTransfer, calculateBalance;
    public JRadioButton checking, savings;
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 180;
    
    public ATMGUI() {
        super("ATM Machine");
        // Setting the width and hight of the box
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Exiting the program once "X" button on the window is pressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Calling the createPanel class to create the components in the box
        createPanel();
        // Adding the JPanel varible to the box
        add(panel);

        setVisible(true);
    }
    
    private void createPanel() {      
        frame = new JFrame("ATM Machine");
        
        // Setting the JButton variable
        calculateWithdraw = new JButton("Withdraw");
        calculateTransfer = new JButton("Transfer To");
        calculateDesposit = new JButton("Deposit");
        calculateBalance = new JButton("Balance");
        
        // Setting the JTextField variables
        money = new JTextField(20);
        
        // Setting the JRadioButton variables
        checking = new JRadioButton("Checking");
        savings = new JRadioButton("Savings");
        
        // calculateWithdraw actionListener
        calculateWithdraw.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                String moneyString;
                int moneyInteger;

                moneyString = money.getText();
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);

                    if(moneyInteger % 20 == 0)
                    {
                        try {
                            int test =0;
                            if(moneyInteger > test) {
                            throw new InsufficientFunds("You do not have enough money to withdraw that amount.");
                            }
                            else {
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
                int moneyInteger;

                moneyString = money.getText();
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Value provided was not "
                            + "an integer, please enter a new value.");
                }
            }
        });
        
        // calculateDesposit actionListener
        calculateDesposit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                String moneyString;
                int moneyInteger;

                moneyString = money.getText();
                if(checkIfInteger(moneyString) == true) {
                    moneyInteger = Integer.parseInt(moneyString);
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
                
            }
        });
        
        frame.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel = new JPanel();
        calculateWithdraw.setAlignmentX(LEFT_ALIGNMENT);
        calculateTransfer.setAlignmentX(LEFT_ALIGNMENT);
        calculateDesposit.setAlignmentX(RIGHT_ALIGNMENT);
        calculateBalance.setAlignmentX(RIGHT_ALIGNMENT);
        
        // Adding all the JLable and JTextField variables to the box
        panel.add(calculateWithdraw);
        panel.add(calculateTransfer);
        panel.add(calculateDesposit);
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
     
    Account checkingAccount = new Account();
    Account savingsAccount = new Account();
    
    new ATMGUI();
     
 }
}

