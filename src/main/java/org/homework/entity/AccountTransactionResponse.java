package org.homework.entity;

import java.util.Objects;

public class AccountTransactionResponse {
    
    private Account accountFromResult;
    
    private Account accountToResult;

    @Override
    public String toString() {
        return "AccountTransactionResponse{" +
                "accountFromResult=" + accountFromResult +
                ", accountToResult=" + accountToResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionResponse that = (AccountTransactionResponse) o;
        return Objects.equals(accountFromResult, that.accountFromResult) && Objects.equals(accountToResult, that.accountToResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountFromResult, accountToResult);
    }

    public Account getAccountFromResult() {
        return accountFromResult;
    }

    public void setAccountFromResult(Account accountFromResult) {
        this.accountFromResult = accountFromResult;
    }

    public Account getAccountToResult() {
        return accountToResult;
    }

    public void setAccountToResult(Account accountToResult) {
        this.accountToResult = accountToResult;
    }
}
