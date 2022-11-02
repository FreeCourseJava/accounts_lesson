package org.homework.entity;

import java.util.Objects;

public class AccountTransactionRequest {
    
    private String accountFrom;
    
    private String accountTo;
    
    private double transactionSum;

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

    @Override
    public String toString() {
        return "AccountTransactionRequest{" +
                "accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", transactionSum=" + transactionSum +
                '}';
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public double getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(double transactionSum) {
        this.transactionSum = transactionSum;
    }
}
