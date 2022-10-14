package org.homework.entity;

import java.util.Objects;

public class Account implements HasKey {

    public String accountName;

    public String currencyAbbrev;

    public double balance;

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", currencyAbbrev='" + currencyAbbrev + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(accountName, account.accountName) && Objects.equals(currencyAbbrev, account.currencyAbbrev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, currencyAbbrev, balance);
    }

    @Override
    public String getKey() {
        return accountName;
    }
}
