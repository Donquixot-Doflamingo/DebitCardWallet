package DebitCardWallet.userInterface;

import DebitCardWallet.Connection.Functionalities;

import javax.swing.*;
import java.awt.*;

public class loginPage extends JDialog {
    private JTextField idFieldLogin;
    private JPasswordField passwordFieldLogin;
    private JButton loginButton;
    private JButton clearButton;
    private JPanel loginPanel;
    private JButton mainMenuButton;
    private JButton exitButton;

    public loginPage(JFrame parent){

        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        loginButton.addActionListener(e -> login());

        clearButton.addActionListener(e -> {
            idFieldLogin.setText("");
            passwordFieldLogin.setText("");
        });

        exitButton.addActionListener(e -> dispose());

        mainMenuButton.addActionListener(e -> {
            dispose();
            application l = new application();
            l.dispose();
        });

        setVisible(true);
    }

    private void login() {
        String id = idFieldLogin.getText();
        String password = String.valueOf(passwordFieldLogin.getPassword());

        idFieldLogin.setText("");
        passwordFieldLogin.setText("");

        Functionalities login = new Functionalities();
        String result = login.checkIfTrueUser(id,password);
        if(id.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter credentials",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }

        else if(!result.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    result,
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "Welcome user '" + id + "'",
                    "Logged In",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            DashBoard d = new DashBoard(id,new JFrame());
            d.dispose();
        }
    }

}
