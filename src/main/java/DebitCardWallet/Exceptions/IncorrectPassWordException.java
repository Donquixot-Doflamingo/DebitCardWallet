package DebitCardWallet.Exceptions;

public class IncorrectPassWordException extends Throwable {
    public IncorrectPassWordException(){
        System.out.println("IncorrectPassWordException - Wrong password of the corresponding name");
    }
}
