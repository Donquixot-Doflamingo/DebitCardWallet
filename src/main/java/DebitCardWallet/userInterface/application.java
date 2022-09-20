package DebitCardWallet.userInterface;

import javax.swing.*;
import java.awt.*;

public class application extends JDialog{
    private JPanel application;
    private JButton registerButton;
    private JButton LoginBtn;
    private JButton exitButton;

    public application() {
        JFrame jf = new JFrame();
        setTitle("Debit card wallet");
        setContentPane(application);
        setMinimumSize(new Dimension(600,500));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        exitButton.addActionListener(e -> dispose());

        LoginBtn.addActionListener(e -> {
            dispose();
            loginPage l = new loginPage(jf);
            l.dispose();
        });

        registerButton.addActionListener(e -> {
            dispose();
            RegistrationForm r = new RegistrationForm(jf);
            r.dispose();
        });
        setVisible(true);
    }

}
