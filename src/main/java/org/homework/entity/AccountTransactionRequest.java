package org.homework.entity;

import java.util.Objects;

public class AccountTransactionRequest {

    public String accountFrom;

    public String accountTo;

    public double transactionSum;

    @Override
    public String toString() {
        return "AccountTransactionRequest{" +
                "accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", transactionSum=" + transactionSum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionRequest that = (AccountTransactionRequest) o;
        return Double.compare(that.transactionSum, transactionSum) == 0 && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountFrom, accountTo, transactionSum);
    }
}
