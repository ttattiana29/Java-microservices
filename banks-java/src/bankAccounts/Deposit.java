package bankAccounts;

import entities.BankAccount;
import entities.Transaction;
import entities.Client;
import entities.Bank;
import tools.BankException;

public class Deposit extends BankAccount{
    public Deposit(double money, double limit, double percent) {
        super(money, limit, percent);
    }

    @Override
    public Transaction makeTransaction(Client client, BankAccount bankAccount1, BankAccount bankAccount2, double money) throws BankException {
        throw new BankException("Sorry, transaction are not available for deposit accounts");
    }

    @Override
    public Transaction withdrawCash(Bank bank, Client client, BankAccount bankAccount, double money) throws BankException {
        throw new BankException("Sorry, you cannot withdraw money from the deposit account");
    }
}
