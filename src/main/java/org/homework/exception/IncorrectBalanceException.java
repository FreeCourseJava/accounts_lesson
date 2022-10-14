package org.homework.exception;

public class IncorrectBalanceException extends RuntimeException {

    public IncorrectBalanceException(double balance, double amount) {
        super(String.format("Balance after transaction is %s. Transaction amount: %s", balance, amount));
    }
}
