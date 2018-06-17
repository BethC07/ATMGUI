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
    // JTextField is an inputable field that can be editied
    public JTextField money;
    // JButton is exactly that, a button
    // It can actually do something if it has a ActionListener
    public JButton calculateWithdraw, calculateDeposit, 
            calculateTransfer, calculateBalance;
    public JRadioButton checking, savings;
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 200;
    public String account = "Checking";
    private double balance;
    Account checkingAccount = new Account("Checking");
    Account savingsAccount = new Account("Savings");
    
    public ATMGUI() {
        super(new BorderLayout());
        
        // Calling the createPanel class to create the components in the box
        createPanel();
    }
    
    private void createPanel() {      
        frame = new JFrame("ATM Machine");
        // Setting the width and hight of the box
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Setting the JButton variable
        calculateWithdraw = new JButton("Withdraw");
        calculateTransfer = new JButton("Transfer To");
        calculateDeposit = new JButton("Deposit");
        calculateBalance = new JButton("Balance");
        
        // Setting the JTextField variables
        money = new JTextField(20);
        money.setMaximumSize(money.getPreferredSize());
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
        
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        JPanel Xpanel1 = new JPanel();
        BoxLayout layout1 = new BoxLayout(Xpanel1, BoxLayout.X_AXIS);
        Xpanel1.setLayout(layout1);
        
        JPanel widthPanel = new JPanel();
        BoxLayout widthLayout = new BoxLayout(widthPanel, BoxLayout.X_AXIS);
        widthPanel.setLayout(widthLayout);
        widthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calculateWithdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
        widthPanel.add(calculateWithdraw);
        Xpanel1.add(widthPanel);
        
        JPanel depositPanel = new JPanel();
        BoxLayout depositLayout = new BoxLayout(depositPanel, BoxLayout.X_AXIS);
        depositPanel.setLayout(depositLayout);
        depositPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calculateDeposit.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(calculateDeposit);
        Xpanel1.add(depositPanel);

        JPanel Xpanel2 = new JPanel();
        BoxLayout layout2 = new BoxLayout(Xpanel2, BoxLayout.X_AXIS);
        Xpanel2.setLayout(layout2);
        
        JPanel transPanel = new JPanel();
        BoxLayout transLayout = new BoxLayout(transPanel, BoxLayout.X_AXIS);
        transPanel.setLayout(transLayout);
        transPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calculateTransfer.setAlignmentX(Component.CENTER_ALIGNMENT);
        transPanel.add(calculateTransfer);
        Xpanel2.add(transPanel);
        
        JPanel balancePanel = new JPanel();
        BoxLayout balanceLayout = new BoxLayout(balancePanel, BoxLayout.X_AXIS);
        balancePanel.setLayout(balanceLayout);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calculateBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
        balancePanel.add(calculateBalance);
        Xpanel2.add(balancePanel);
        
        JPanel panel3 = new JPanel();
        BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.X_AXIS);
        panel3.setLayout(layout3);
        panel3.add(checking);
        panel3.add(savings);
        
        JPanel panel4 = new JPanel();
        BoxLayout layout4 = new BoxLayout(panel4, BoxLayout.Y_AXIS);
        money.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.setLayout(layout4);
        panel4.add(money);
        
        panel.add(Xpanel1);
        panel.add(Xpanel2);
        panel.add(panel3);
        panel.add(panel4);
        frame.add(panel);
        
        // Exiting the program once "X" button on the window is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
