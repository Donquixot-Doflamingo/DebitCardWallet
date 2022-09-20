package supportClassesTest;

import DebitCardWallet.supportClasses.Validation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@PrepareForTest(Validation.class)
public class ValidationsTest {
    Validation validity;

    @Before
    void initil(){
        validity = new Validation();
    }

    @Test
    void cardLength(){
    }

}
