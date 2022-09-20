package DebitCardWallet.Exceptions;

public class AmountGreaterThanBalance extends Throwable {
    public AmountGreaterThanBalance() {
        System.out.println("The amount of withdrawal was found greater than the balance");
    }

}
