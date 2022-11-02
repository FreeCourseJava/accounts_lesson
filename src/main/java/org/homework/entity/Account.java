package org.homework.entity;

import java.util.Objects;

public class Account implements HasKey {
    
    private Long accountId;
    private String accountName;
    
    private Long currencyId;
    
    @Deprecated
    private String currencyAbbrev;
    
    private double balance;

    @Deprecated
    public String getCurrencyAbbrev() {
        return currencyAbbrev;
    }

    @Deprecated
    public void setCurrencyAbbrev(String currencyAbbrev) {
        this.currencyAbbrev = currencyAbbrev;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", currencyAbbrev='" + currencyId + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(accountId, account.accountId) && Objects.equals(accountName, account.accountName) && Objects.equals(currencyId, account.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountName, currencyId, balance);
    }

    @Override
    public String getKey() {
        return accountName;
    }

    @Override
    public Long getId() {
        return accountId;
    }
}
