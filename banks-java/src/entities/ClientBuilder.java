package entities;

import java.util.ArrayList;

public class ClientBuilder {
    private String firstName;
    private String lastName;
    private String address;
    private String passport;
    private ArrayList<BankAccount> listAccounts;
    public ClientBuilder addFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder addLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder addAddress(String address)
    {
        this.address = address;
        return this;
    }

    public ClientBuilder addPassport(String passport)
    {
        this.passport = passport;
        return this;
    }

    public ClientBuilder addListAccounts(ArrayList<BankAccount> bankAccounts)
    {
        this.listAccounts = bankAccounts;
        return this;
    }

    public Client toBuild()
    {
        Client finalClient = new Client(firstName, lastName);
        return finalClient;
    }
}
