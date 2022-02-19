package bankAccounts;

import entities.BankAccount;

public class Credit extends BankAccount{
    private double OwesMoney;
    public Credit(double money, double limit, double percent, double owesMoney) {
        super(money, limit, percent);
        OwesMoney = owesMoney;
    }

    public double getOwesMoney() {
        return OwesMoney;
    }

    public void setOwesMoney(double owesMoney) {
        OwesMoney = owesMoney;
    }

    @Override
    public void countPercent(int amountMonth) {
        OwesMoney += ((OwesMoney / 100) * getPercent()) * amountMonth;
    }
}
