package org.homework.controller.impl;

import java.util.Scanner;
import java.util.function.Supplier;

import org.homework.controller.AccountController;
import org.homework.di.annotation.EntryPoint;
import org.homework.di.annotation.Service;
import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectInputException;
import org.homework.service.AccountService;

@Service
public class AccountControllerImpl implements AccountController {

    private static final boolean TEST_RUN = true;

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @EntryPoint
    public void receiveCommand() {

        if (TEST_RUN) {
            testRun();
        } else {
            processCommand();
        }

    }

    private void testRun() {
        AccountTransactionRequest accountTransactionRequest = new AccountTransactionRequest();
        accountTransactionRequest.setAccountFrom("vasja");
        accountTransactionRequest.setAccountTo("petja");
        accountTransactionRequest.setTransactionSum(100d);

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
        accountTransactionRequest.setAccountFrom(firstAccount);
        accountTransactionRequest.setAccountTo(secondAccount);
        accountTransactionRequest.setTransactionSum(transferAmount);

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
