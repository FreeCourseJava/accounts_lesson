package org.homework.service;

import org.homework.entity.Account;
import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectBalanceException;
import org.homework.repository.AccountRepository;

public class AccountService {

    public static AccountTransactionResponse doTransaction(AccountTransactionRequest accountTransactionRequest) {
        if (Double.compare(accountTransactionRequest.transactionSum, 0d) < 0) {
            throw new IllegalArgumentException("Transaction sum before zero");
        }
        Account accountFrom = AccountRepository.load(accountTransactionRequest.accountFrom);
        Account accountTo = AccountRepository.load(accountTransactionRequest.accountTo);

        withdrawSum(accountFrom, accountTransactionRequest.transactionSum);

        addSum(accountTo, accountFrom, accountTransactionRequest.transactionSum);
        AccountRepository.save(accountTo);
        AccountRepository.save(accountFrom);

        return buildResponse(accountFrom, accountTo);
    }

    private static AccountTransactionResponse buildResponse(Account accountFrom, Account accountTo) {
        AccountTransactionResponse accountTransactionResponse = new AccountTransactionResponse();
        accountTransactionResponse.accountFromResult = accountFrom;
        accountTransactionResponse.accountToResult = accountTo;
        return accountTransactionResponse;
    }

    private static void addSum(Account accountTo, Account accountFrom, double sum) {
        if (!accountTo.currencyAbbrev.equals(accountFrom.currencyAbbrev)) {
            sum = CurrencyService.convert(accountFrom.currencyAbbrev, accountTo.currencyAbbrev, sum);
        }
        accountTo.balance = accountTo.balance + sum;
    }

    private static void withdrawSum(Account account, double sum) {
        account.balance = account.balance - sum;
        if (Double.compare(account.balance, 0d) < 0) {
            throw new IncorrectBalanceException(account.balance, sum);
        }
    }
}
