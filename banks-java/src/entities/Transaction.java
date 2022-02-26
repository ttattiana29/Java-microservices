package entities;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private BankAccount bankAccount1;
    private BankAccount bankAccount2;
    private double money;

    public Transaction(BankAccount bankaccount1, BankAccount bankAccount2, double money){
        this.id = UUID.randomUUID();
        this.bankAccount1 = bankaccount1;
        this.bankAccount2 = bankAccount2;
        this.money = money;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BankAccount getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(BankAccount bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public BankAccount getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(BankAccount bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
