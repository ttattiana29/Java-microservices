package bankAccounts;

import entities.BankAccount;

public class Credit extends BankAccount{
    private double owesMoney;
    public Credit(double money, double limit, double percent, double owesMoney) {
        super(money, limit, percent);
        this.owesMoney = owesMoney;
    }

    public double getOwesMoney() {
        return owesMoney;
    }

    public void setOwesMoney(double owesMoney) {
        this.owesMoney = owesMoney;
    }

    @Override
    public void countPercent(int amountMonth) {
        owesMoney += ((owesMoney / 100) * getPercent()) * amountMonth;
    }
}
