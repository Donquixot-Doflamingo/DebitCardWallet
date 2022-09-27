package DebitCardWallet.userInterface;

import DebitCardWallet.Connection.Functionalities;

import javax.swing.*;
import java.awt.*;

public class DashBoard extends JDialog {
    private JPanel DashBoard;
    private JTextField withdrawAmountField;
    private JButton withdrawBtn;
    private JButton depositBtn;
    private JTextField depositAmountField;
    private JLabel showBalLabel;
    private JLabel dashBoardLabel;
    private JButton logOutButton;
    private JButton mainMenuBtn;
    private JButton ExitBtn;

    private final Functionalities func = new Functionalities();

    private final String id;

    public DashBoard(String id, JFrame parent) {
        super(parent);
        JDialog.setDefaultLookAndFeelDecorated(true);
        setTitle("Dashboard");
        setContentPane(DashBoard);
        setMinimumSize(new Dimension(600,500));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        this.id = id;

        dashBoardLabel.setText("Welcome to the Wallet -: " + id);

        showBalance();

        withdrawBtn.addActionListener(e -> withdrawAction());

        depositBtn.addActionListener(e -> depositAction());

        logOutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "You have been logged out successfully",
                        "Logged out",
                        JOptionPane.OK_OPTION);
                dispose();
                loginPage l = new loginPage(parent);
                l.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }

        });

        mainMenuBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to log out and return to main menu?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "You have been logged out successfully. Returning to main menu.",
                        "Logged out",
                        JOptionPane.OK_OPTION);
                dispose();
                application app = new application();
                app.dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }

        });

        ExitBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to exit the application?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "Application will be closed",
                        "Shutting down",
                        JOptionPane.OK_OPTION);
                dispose();
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }
        });

        setVisible(true);
    }
    void depositAction(){
        String deposit = depositAmountField.getText();
        depositAmountField.setText("");

        if(deposit.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter the amount",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        } else if (func.isOnlyNumber(deposit)) {
            JOptionPane.showMessageDialog(this,
                    "Please enter only digits",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount = Double.parseDouble(deposit);

        if(amount <= 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter the deposit amount or choose withdrawal",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }

        String result = func.depositBalance(id,amount);

        JOptionPane.showMessageDialog(this,
                result,
                "Deposit success",
                JOptionPane.ERROR_MESSAGE);
        showBalance();
    }
    void withdrawAction(){
        String withdraw = withdrawAmountField.getText();
        withdrawAmountField.setText("");

        if(withdraw.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter the amount",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if (func.isOnlyNumber(withdraw)) {
            JOptionPane.showMessageDialog(this,
                    "Please enter only digits",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        double amount = Double.parseDouble(withdraw);
        if(amount <= 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter the withdrawal amount or choose deposit",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }

        String[] result = func.withDrawBalance(id, amount);

        if(result[0].equals("false")){
            JOptionPane.showMessageDialog(this,
                    result[1],
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,
                    result[1],
                    "Withdrawal success",
                    JOptionPane.ERROR_MESSAGE);
        }

        showBalance();
    }
    void showBalance(){
        String result = func.currentBalance(id) + " Rs.";
        showBalLabel.setText(result);
    }

}
