package tools;

import entities.*;

import java.util.Objects;
import java.util.Scanner;
import static java.lang.System.out;

public class Program {
    public static Scanner scanner = new Scanner(System.in);
    private final static CentralBank centralBank = new CentralBank("Moscow Central Bank");

    public static void main(String[] args) throws BankException {
        Client oldClient = centralBank.createClient("Ivan", "Ivanov");
        Bank sberbank = centralBank.addBank("Sberbank", 0, 2, 3, 5000, 15000, 15000);
        Bank tinkoff = centralBank.addBank("Tinkoff", 1, 1, 4, 6000, 20000, 20000);
        centralBank.addClientBank(oldClient, tinkoff);
        BankAccount bankAccount = centralBank.createCreditAccountForClient(tinkoff, oldClient, 10000);

        out.println("Write your first name");
        String firstName = scanner.next();
        out.println("Write your last name");
        String lastName = scanner.next();
        Client client = centralBank.createClient(firstName, lastName);
        out.println("Do you want to register your address? y/n");
        String chooseAddress = scanner.next();
        if (Objects.equals(chooseAddress, "y")) {
            out.println("Enter your address");
            String address = scanner.next();
            centralBank.addClientAddress(client, address);
        }

        out.println("Do you want to register your passport? y/n");
        String choosePassport = scanner.next();
        if (Objects.equals(choosePassport, "y")) {
            out.println("Enter your passport");
            String passport = scanner.next();
            centralBank.addClientAddress(client, passport);
        }

        out.println("Select the bank where you want to register: ");
        int i = 0;
        for (Bank allbank : centralBank.getBanks()) {
            out.println((i+1) + allbank.getName());
        }
        String enteredBank = scanner.next();
        Bank bank = centralBank.getBanks().stream().filter(banks -> banks.getName().equals(enteredBank)).findAny().orElse(null);
        centralBank.addClientBank(client, bank);
        out.println("Select the account where you want to register: " +
                "1) Debit" +
                "2) Deposit" +
                "3) Credit" +
                "Write one number");
        BankAccount card = null;
        String result = scanner.next();
        int resultFor;
        if (Objects.equals(result, "1")) {
            out.println("Enter how much do you want to put in your debit account?");
            resultFor = scanner.nextInt();
            int money = resultFor;
            card = centralBank.createDebitAccountForClient(bank, client, money);
        }
        else if (Objects.equals(result, "2"))
        {
            out.println("Enter how much do you want to put in your deposit account?");
            resultFor = scanner.nextInt();
            int money = resultFor;
            card = centralBank.createDepositAccountForClient(bank, client, money);
        }
        else if (Objects.equals(result, "3"))
        {
            out.println("Enter how much do you want to recieve for your credit account?");
            resultFor = scanner.nextInt();
            int money = resultFor;
            card = centralBank.createCreditAccountForClient(bank, client, money);
        }

        out.println("Do you want to transaction to someone?(y/n)");
        String res = scanner.next();
        if (Objects.equals(res, "y"))
        {
            out.println("How much money do you want to transaction?");
            double moneyTrans = scanner.nextDouble();
            Transaction transaction = card.makeTransaction(client, card, bankAccount, moneyTrans);
        }
        else if (Objects.equals(res, "n"))
        {
            out.println("Thanks for you choice. Bye Bye!!!");
        }

        out.println("Money in your card");
        out.println(card.getMoney());
        out.println("Thanks for you choice. Bye Bye!!!");
    }
}
