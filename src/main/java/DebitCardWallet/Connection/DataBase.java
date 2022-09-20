package DebitCardWallet.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    protected static final String dbName = "Wallet";
    protected static final String url = "jdbc:mysql://localhost:3306/"+dbName;
    protected static final String username = "root";
    protected static final String password = "Harshk.4";
    protected static final String className = "com.mysql.cj.jdbc.Driver";
    protected static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static final String tableName = "userDetails";
    protected static final String ID = "Id";
    protected static final String NAME = "Name";
    protected static final String PASSWORD = "Password";
    protected static final String CARD = "Card";
    protected static final String CVV = "Cvv";
    protected static final String DATE_OF_EXPIRY = "DateOfExpiry";
    protected static final String BALANCE = "Balance";

}
