package entities;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private UUID id;
    private String name;
    private double percentDebit;
    private double percentCredit;
    private double percentDeposit;
    private double limitDebit;
    private double limitCredit;
    private double limitDeposit;
    private ArrayList<Client> clients;
    private ArrayList<BankAccount> bankAccounts;

    public Bank(String name,
                double percentDebit,
                double percentCredit,
                double percentDeposit,
                double limitDebit,
                double limitCredit,
                double limitDeposit)
    {
        this.id = UUID.randomUUID();
        this.name = name;
        this.percentDebit = percentDebit;
        this.percentCredit = percentCredit;
        this.percentDeposit = percentDeposit;
        this.limitDebit = limitDebit;
        this.limitCredit = limitCredit;
        this.limitDeposit = limitDeposit;
        this.bankAccounts = new ArrayList<BankAccount>();
        this.clients = new ArrayList<Client>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentDebit() {
        return percentDebit;
    }

    public void setPercentDebit(double percentDebit) {
        this.percentDebit = percentDebit;
    }

    public double getPercentCredit() {
        return percentCredit;
    }

    public void setPercentCredit(double percentCredit) {
        this.percentCredit = percentCredit;
    }

    public double getPercentDeposit() {
        return percentDeposit;
    }

    public void setPercentDeposit(double percentDeposit) {
        this.percentDeposit = percentDeposit;
    }

    public double getLimitDebit() {
        return limitDebit;
    }

    public void setLimitDebit(double limitDebit) {
        this.limitDebit = limitDebit;
    }

    public double getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(double limitCredit) {
        this.limitCredit = limitCredit;
    }

    public double getLimitDeposit() {
        return limitDeposit;
    }

    public void setLimitDeposit(double limitDeposit) {
        this.limitDeposit = limitDeposit;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void countPercent(int amountMonth) {
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.countPercent(amountMonth);
        }
    }
}


