package org.homework.service.impl;

import org.homework.entity.Account;
import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectBalanceException;
import org.homework.repository.AccountRepository;
import org.homework.service.AccountService;
import org.homework.service.CurrencyService;

public class AccountServiceImpl implements AccountService {

    private final CurrencyService currencyService;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(CurrencyService currencyService, AccountRepository accountRepository) {
        this.currencyService = currencyService;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountTransactionResponse doTransaction(AccountTransactionRequest accountTransactionRequest) {
        if (Double.compare(accountTransactionRequest.transactionSum, 0d) < 0) {
            throw new IllegalArgumentException("Transaction sum before zero");
        }
        Account accountFrom = accountRepository.load(accountTransactionRequest.accountFrom);
        Account accountTo = accountRepository.load(accountTransactionRequest.accountTo);

        withdrawSum(accountFrom, accountTransactionRequest.transactionSum);

        addSum(accountTo, accountFrom, accountTransactionRequest.transactionSum);
        accountRepository.save(accountTo);
        accountRepository.save(accountFrom);

        return buildResponse(accountFrom, accountTo);
    }

    private AccountTransactionResponse buildResponse(Account accountFrom, Account accountTo) {
        AccountTransactionResponse accountTransactionResponse = new AccountTransactionResponse();
        accountTransactionResponse.accountFromResult = accountFrom;
        accountTransactionResponse.accountToResult = accountTo;
        return accountTransactionResponse;
    }

    private void addSum(Account accountTo, Account accountFrom, double sum) {
        if (!accountTo.currencyAbbrev.equals(accountFrom.currencyAbbrev)) {
            sum = currencyService.convert(accountFrom.currencyAbbrev, accountTo.currencyAbbrev, sum);
        }
        accountTo.balance = accountTo.balance + sum;
    }

    private void withdrawSum(Account account, double sum) {
        account.balance = account.balance - sum;
        if (Double.compare(account.balance, 0d) < 0) {
            throw new IncorrectBalanceException(account.balance, sum);
        }
    }
}
