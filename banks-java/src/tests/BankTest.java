package tests;

import entities.CentralBank;
import entities.Bank;
import entities.Client;
import entities.BankAccount;
import entities.Transaction;
import bankAccounts.Credit;
import tools.BankException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
    private CentralBank _centralBank;
    final double delta = 0.000001;

    @Before
    public void setUp() {
        _centralBank = new CentralBank("Moscow Central Bank");
    }

    @Test
    public void makeTransaction() throws BankException {
        double money = 10000;
        double money2 = 500;
        Bank sberbank = _centralBank.addBank("Sberbank", 0, 2, 3, 5000, 15000, 15000);
        Bank tinkoff = _centralBank.addBank("Tinkoff", 1, 1, 4, 6000, 20000, 20000);
        Client client1 = _centralBank.createClient("Tanya", "Rebrova");
        Client client2 = _centralBank.createClient("Petr", "Petrovich");
        _centralBank.addClientBank(client1, sberbank);
        _centralBank.addClientBank(client2, tinkoff);
        BankAccount account1 = _centralBank.createDebitAccountForClient(sberbank, client1, money);
        BankAccount account2 = _centralBank.createCreditAccountForClient(tinkoff, client2, money2);
        Transaction transaction = account1.makeTransaction(client1, account1, account2, 1000);
        Assert.assertEquals(9000, account1.getMoney(), delta);
        Assert.assertEquals(1500, account2.getMoney(), delta);
    }

    @Test
    public void cancelTransaction() throws BankException {
        double money = 10000;
        double money2 = 500;
        Bank sberbank = _centralBank.addBank("Sberbank", 0, 2, 3, 5000, 15000, 15000);
        Bank tinkoff = _centralBank.addBank("Tinkoff", 1, 1, 4, 6000, 20000, 20000);
        Client client1 = _centralBank.createClient("Tanya", "Rebrova");
        Client client2 = _centralBank.createClient("Petr", "Petrovich");
        _centralBank.addClientBank(client1, sberbank);
        _centralBank.addClientBank(client2, tinkoff);
        BankAccount account1 = _centralBank.createDebitAccountForClient(sberbank, client1, money);
        BankAccount account2 = _centralBank.createCreditAccountForClient(tinkoff, client2, money2);
        Transaction transaction = account1.makeTransaction(client1, account1, account2, 1000);
        _centralBank.addTransaction(transaction);
        _centralBank.cancelTransaction(transaction.getId());
        Assert.assertEquals(10000, account1.getMoney(), delta);
    }

    @Test
    public void toSeeHowMuchMoneyInMonth() throws BankException {
        double money = 10000;
        double money2 = 500;
        Bank sberbank = _centralBank.addBank("Sberbank", 0, 2, 3, 5000, 15000, 15000);
        Bank tinkoff = _centralBank.addBank("Tinkoff", 1, 1, 4, 6000, 20000, 20000);
        Client client1 = _centralBank.createClient("Tanya", "Rebrova");
        Client client2 = _centralBank.createClient("Petr", "Petrovich");
        _centralBank.addClientBank(client1, sberbank);
        _centralBank.addClientBank(client2, tinkoff);
        BankAccount account1 = _centralBank.createDebitAccountForClient(sberbank, client1, money);
        BankAccount account2 = _centralBank.createCreditAccountForClient(tinkoff, client2, money2);
        _centralBank.allCountPercent(124);
        Assert.assertEquals(520, ((Credit) account2).getOwesMoney(), delta);
        Assert.assertEquals(10000, account1.getMoney(), delta);
    }
}

