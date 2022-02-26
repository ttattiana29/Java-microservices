package entities;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String passport;
    private List<BankAccount> listAccounts;
    public Client(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = null;
        this.passport = null;
        this.listAccounts = new ArrayList<BankAccount>();
    }

    public ClientBuilder toBuilder(ClientBuilder clientBuilder)
    {
        clientBuilder.addFirstName(firstName);
        clientBuilder.addLastName(lastName);
        clientBuilder.addAddress(address);
        clientBuilder.addPassport(passport);
        return clientBuilder;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public List<BankAccount> getListAccounts() {
        return listAccounts;
    }

    public void setListAccounts(List<BankAccount> listAccounts) {
        this.listAccounts = listAccounts;
    }

    public List<BankAccount> addInListAccount(BankAccount bankAccount) {
        listAccounts.add(bankAccount);
        return listAccounts;
    }
}