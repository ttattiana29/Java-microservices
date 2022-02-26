package bankAccounts;

import entities.BankAccount;

public class Debit extends BankAccount {
    public Debit(double money, double limit, double percent) {
        super(money, limit, percent);
    }
}
