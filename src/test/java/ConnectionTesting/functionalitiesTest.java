package ConnectionTesting;

import DebitCardWallet.Connection.Functionalities;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.sql.DriverManager;

@PrepareForTest({Functionalities.class, DriverManager.class})
public class functionalitiesTest {

    Functionalities functionalities;
    
    @Before
    public void initializer(){
        functionalities = new Functionalities();
    }
    
    @Test
    public void TestingThings(){
        
    }
    
    @After
    public void cleaUP(){
        functionalities = null;
    }
    
}
