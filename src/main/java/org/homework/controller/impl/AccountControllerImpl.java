package org.homework.controller.impl;

import java.util.Scanner;
import java.util.function.Supplier;

import org.homework.controller.AccountController;
import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectInputException;
import org.homework.service.AccountService;
import org.homework.service.impl.AccountServiceImpl;

public class AccountControllerImpl implements AccountController {

    private static final boolean TEST_RUN = true;

    private static AccountControllerImpl instance;

    private final AccountService accountService;

    private AccountControllerImpl() {
        this.accountService = AccountServiceImpl.getInstance();
    }

    public static AccountControllerImpl getInstance() {
        if (instance == null) {
            instance = new AccountControllerImpl();
        }
        return instance;
    }

    @Override
    public void receiveCommand() {

        if (TEST_RUN) {
            testRun();
        } else {
            processCommand();
        }

    }

    private void testRun() {
        AccountTransactionRequest accountTransactionRequest = new AccountTransactionRequest();
        accountTransactionRequest.accountFrom = "vasja";
        accountTransactionRequest.accountTo = "petja";
        accountTransactionRequest.transactionSum = 100d;

        AccountTransactionResponse response = accountService.doTransaction(accountTransactionRequest);
        System.out.println("Response = " + response);
    }

    private void processCommand() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter first account name:");
        String firstAccount = getFromKeyboard(() -> scanner.nextLine());

        System.out.println("Please enter second account name:");
        String secondAccount = getFromKeyboard(() -> scanner.nextLine());

        System.out.println("Please enter transaction sum:");
        double transferAmount = getFromKeyboard(() -> Double.parseDouble(scanner.nextLine()));

        AccountTransactionRequest accountTransactionRequest = new AccountTransactionRequest();
        accountTransactionRequest.accountFrom = firstAccount;
        accountTransactionRequest.accountTo = secondAccount;
        accountTransactionRequest.transactionSum = transferAmount;

        AccountTransactionResponse accountTransactionResponse = accountService.doTransaction(accountTransactionRequest);

        System.out.println("Operation successful. Result: " + accountTransactionResponse);
    }

    private <T> T getFromKeyboard(Supplier<T> supplier) {
        for (int i = 0; i < 10; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                System.out.println("Incorrect input. Please try again");
            }
        }
        throw new IncorrectInputException("Unable to get input from keyboard");
    }
}
