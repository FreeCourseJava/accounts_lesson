package org.homework.controller;

import java.util.Scanner;
import java.util.function.Supplier;

import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectInputException;
import org.homework.service.AccountService;

public class AccountController {

    private static final boolean TEST_RUN = true;


    public static void receiveCommand() {

        if (TEST_RUN) {
            testRun();
        } else {
            processCommand();
        }

    }

    private static void testRun() {
        AccountTransactionRequest accountTransactionRequest = new AccountTransactionRequest();
        accountTransactionRequest.accountFrom = "vasja";
        accountTransactionRequest.accountTo = "petja";
        accountTransactionRequest.transactionSum = 100d;

        AccountTransactionResponse response = AccountService.doTransaction(accountTransactionRequest);
        System.out.println("Response = " + response);
    }

    private static void processCommand() {
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

        AccountTransactionResponse accountTransactionResponse = AccountService.doTransaction(accountTransactionRequest);

        System.out.println("Operation successful. Result: " + accountTransactionResponse);
    }

    private static <T> T getFromKeyboard(Supplier<T> supplier) {
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
