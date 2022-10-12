package org.homework.service.impl;

import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;
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
        // You transaction logic here
        return null;
    }
}
