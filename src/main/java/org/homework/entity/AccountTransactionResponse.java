package org.homework.entity;

import java.util.Objects;

public class AccountTransactionResponse {

    public Account accountFromResult;

    public Account accountToResult;

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
}
