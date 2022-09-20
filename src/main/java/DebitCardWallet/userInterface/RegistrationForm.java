package DebitCardWallet.userInterface;

import DebitCardWallet.Connection.Registration;
import DebitCardWallet.supportClasses.User;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JDialog {

    private JTextField nameField;
    private JTextField idField;
    private JTextField cardField;
    private JTextField cvvField;
    private JTextField dateField;
    private JTextField balanceField;
    private JPasswordField passwordField;
    private JButton registerBtn;
    private JButton clearBtn;
    private JPanel registerPanel;
    private JButton mainMenuButton;
    private JButton exitButton;


    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Registration");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerBtn.addActionListener(e -> registerUser());

        clearBtn.addActionListener(e -> {
            nameField.setText("");
            idField.setText("");
            passwordField.setText("");
            cardField.setText("");
            cvvField.setText("");
            dateField.setText("");
            balanceField.setText("");
        });

        mainMenuButton.addActionListener(e -> {
            dispose();
            application a = new application();
            a.dispose();
        });

        exitButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    void registerUser(){
        String name = nameField.getText();
        String id = idField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String card = cardField.getText();
        String cvv = cvvField.getText();
        String date = dateField.getText();
        String balance = balanceField.getText();

        Registration register = new Registration();

        if(name.isEmpty() || id.isEmpty() || password.isEmpty() || card.isEmpty() || cvv.isEmpty() || date.isEmpty() || balance.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all values",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cardResult = register.cardLength(card);
        String cvvResult = register.cvvLength(cvv);
        String balResult = register.balanceLength(balance);
        String dateResult = register.dateCheck(date);
        String idResult = register.idCheck(id);

        String finalResult = idResult + "\n" + cardResult + "\n" + cvvResult + "\n" + balResult + "\n" + dateResult;
        System.out.println(finalResult);

        if(!idResult.isEmpty() || !cardResult.isEmpty() || !cvvResult.isEmpty() || !balResult.isEmpty() || !dateResult.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    finalResult,
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            User newUser = new User(name,id,card,cvv,date,password,balance);

            register.register(newUser);

            JOptionPane.showMessageDialog(this,
                    "Your Registration is successful. Please login!",
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
            loginPage app = new loginPage(new JFrame());
            app.dispose();
        }
    }
}
