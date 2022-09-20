package ConnectionTesting;

import DebitCardWallet.Connection.DataBase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.*;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataBase.class,DriverManager.class})
public class DatabaseTest extends DataBase{
    static String dbName = "Wallet";
    static String url = "jdbc:mysql://localhost:3306/"+dbName;
    static String username = "root";
    static String password = "Harshk.4";
    String className = "com.mysql.cj.jdbc.Driver";

    String tableName = "userDetails";
    String ID = "Id";
    String NAME = "Name";
    String PASSWORD = "Password";
    String CARD = "Card";
    String CVV = "Cvv";
    String DATE_OF_EXPIRY = "DateOfExpiry";
    String BALANCE = "Balance";
    protected static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void allTableValues(){
        assertEquals(tableName,super.tableName, "Checking database table name");
        assertEquals(ID,super.ID, "Checking database ID column name");
        assertEquals(NAME,super.NAME, "Checking database NAME column name");
        assertEquals(PASSWORD,super.PASSWORD, "Checking database PASSWORD column name");
        assertEquals(CARD,super.CARD, "Checking database CARD column name");
        assertEquals(CVV,super.CVV, "Checking database CVV column name");
        assertEquals(DATE_OF_EXPIRY,super.DATE_OF_EXPIRY, "Checking database DATE_OF_EXPIRY column name");
        assertEquals(BALANCE,super.BALANCE, "Checking database BALANCE column name");

    }

    @Test
    void databaseValues(){
        assertEquals(dbName,super.dbName, "Checking database name");
        assertEquals(username,super.username, "Checking database username");
        assertEquals(password,super.password, "Checking database password");
        assertEquals(className,super.className, "Checking database className");
        assertEquals(tableName,super.tableName, "Checking database table name");
    }

//    @Test
//    void connectionTest() throws Exception {
//        Connection cMock = createMock(Connection.class);
//        mockStatic(DriverManager.class);
//        String connectUrl = url + username + password;
//        expect(DriverManager.getConnection(connectUrl)).andReturn(
//                cMock);
//        replay(DriverManager.class);
//    }
}
