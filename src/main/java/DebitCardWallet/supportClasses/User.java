package DebitCardWallet.supportClasses;

public class User {
    private final String Name;
    private String Id;
    private final String Card;
    private final String Cvv;
    private final String DateOfExpiry;
    private final String Password;

    private final String Balance;

    public String getBalance() {
        return Balance;
    }


    // Constructor - to make the object
    public User(String name, String id, String card, String cvv, String dateOfExpiry, String password,String Balance) {
        this.Name = name;
        this.Id = id;
        this.Card = card;
        this.Cvv = cvv;
        this.DateOfExpiry = dateOfExpiry;
        this.Password = password;
        this.Balance = Balance;
    }

    // Getters and Setters

    public String getName() {
        return Name;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCard() {
        return Card;
    }

    public String getCvv() {
        return Cvv;
    }

    public String getDateOfExpiry() {
        return DateOfExpiry;
    }

    public String getPassword() {
        return Password;
    }


}
