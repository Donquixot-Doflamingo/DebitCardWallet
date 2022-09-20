package DebitCardWallet.Connection;

import DebitCardWallet.supportClasses.User;
import DebitCardWallet.supportClasses.Validation;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration extends Validation {

    public void register(User user) {
        try{
            Class.forName(className);
        }

        catch (ClassNotFoundException e){
            e.printStackTrace();
            return;
        }

        try{
            con = DriverManager.getConnection(url,username,password);

            String sql = "INSERT INTO "+ tableName +" VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getCard());
            ps.setString(5,user.getCvv());
            ps.setString(6,user.getDateOfExpiry());
            ps.setString(7,user.getBalance());

            System.out.println("Processing your Request "+ user.getName() + "...");
            ps.executeUpdate();
            System.out.println("Account Created");
            System.out.println("Please Login");
        } catch (SQLException e) {
            System.out.println("Connection failed during registration");
            e.printStackTrace();
        }
    }

}
