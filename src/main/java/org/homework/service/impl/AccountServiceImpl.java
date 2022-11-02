package org.homework.service.impl;

import org.homework.di.annotation.Service;
import org.homework.entity.Account;
import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
import org.homework.exception.IncorrectBalanceException;
import org.homework.repository.AccountRepository;
import org.homework.service.AccountService;
import org.homework.service.CurrencyService;

@Service
public class AccountServiceImpl implements AccountService {

    private final CurrencyService currencyService;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(CurrencyService currencyService, AccountRepository accountRepository) {
        this.currencyService = currencyService;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountTransactionResponse doTransaction(AccountTransactionRequest accountTransactionRequest) {
        if (Double.compare(accountTransactionRequest.getTransactionSum(), 0d) < 0) {
            throw new IllegalArgumentException("Transaction sum before zero");
        }
        Account accountFrom = accountRepository.load(accountTransactionRequest.getAccountFrom());
        Account accountTo = accountRepository.load(accountTransactionRequest.getAccountTo());

        withdrawSum(accountFrom, accountTransactionRequest.getTransactionSum());

        addSum(accountTo, accountFrom, accountTransactionRequest.getTransactionSum());
        accountRepository.save(accountTo);
        accountRepository.save(accountFrom);

        return buildResponse(accountFrom, accountTo);
    }

    private AccountTransactionResponse buildResponse(Account accountFrom, Account accountTo) {
        AccountTransactionResponse accountTransactionResponse = new AccountTransactionResponse();
        accountTransactionResponse.setAccountFromResult(accountFrom);
        accountTransactionResponse.setAccountToResult(accountTo);
        return accountTransactionResponse;
    }

    private void addSum(Account accountTo, Account accountFrom, double sum) {
        if (!accountTo.getCurrencyId().equals(accountFrom.getCurrencyId())) {
            sum = currencyService.convert(accountFrom.getCurrencyId(), accountTo.getCurrencyId(), sum);
        }
        accountTo.setBalance(accountTo.getBalance() + sum);
    }

    private void withdrawSum(Account account, double sum) {
        account.setBalance(account.getBalance() - sum);
        if (Double.compare(account.getBalance(), 0d) < 0) {
            throw new IncorrectBalanceException(account.getBalance(), sum);
        }
    }
}
