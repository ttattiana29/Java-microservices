package entities;

import tools.BankException;
import bankAccounts.Debit;
import java.util.Objects;
import java.util.UUID;

public abstract class BankAccount {
    private UUID id;
    private double money;
    private double limit;
    private double percent;
    public BankAccount(double money, double limit, double percent) {
        this.id = UUID.randomUUID();
        this.money = money;
        this.limit = limit;
        this.percent = percent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Transaction makeTransaction(Client client,
                                       BankAccount bankAccount1,
                                       BankAccount bankAccount2,
                                       double money) throws BankException {
        if (!Objects.equals(client.getPassport(), "") && bankAccount1.getLimit() < money) {
            throw new BankException("Sorry, you are not fully registered client");
        }

        if (bankAccount1 instanceof Debit && bankAccount1.getMoney() < 0) {
            throw new BankException("Sorry, insufficient funds");
        }

        Transaction transaction = new Transaction(bankAccount1, bankAccount2, money);
        bankAccount1.setMoney(bankAccount1.getMoney() - money);
        bankAccount2.setMoney(bankAccount2.getMoney() + money);
        return transaction;
    }

    public Transaction withdrawCash(Bank bank, Client client, BankAccount bankAccount, double money) throws BankException {
        Transaction transaction = new Transaction(bankAccount, null, money);
        if (bank.getClients().stream().filter(anyClient -> anyClient == client).findFirst() == null) {
            throw new BankException("Sorry, client not found this bank");
        }

        if (client.getListAccounts().stream().filter(anyBankAccount -> anyBankAccount == bankAccount).findFirst() == null) {
            throw new BankException("Sorry, client don't have this bank account");
        }

        bankAccount.setMoney(bankAccount.getMoney() - money);
        return transaction;
    }

    public void topUpCash(Bank bank, Client client, BankAccount bankAccount, double money) throws BankException {
        if (bank.getClients().stream().filter(anyClient -> anyClient == client) == null) {
            throw new BankException("Sorry, client not found this bank");
        }

        if (client.getListAccounts().stream().filter(anyBankAccount -> anyBankAccount == bankAccount) == null) {
            throw new BankException("Sorry, client don't have this bank account");
        }

        bankAccount.setMoney(bankAccount.getMoney() + money);
    }

    public void countPercent(int amountMonth)
    {
        money += ((money / 100) * percent) * amountMonth;
    }
}