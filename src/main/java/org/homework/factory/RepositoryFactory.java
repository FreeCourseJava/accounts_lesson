package org.homework.factory;

import org.homework.repository.AccountRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.repository.impl.AccountRepositoryImpl;
import org.homework.repository.impl.CurrencyRepositoryImpl;

public class RepositoryFactory {

    private AccountRepository accountRepository;
    private CurrencyRepository currencyRepository;

    RepositoryFactory() {
    }

    public AccountRepository getAccountRepository() {
        if (accountRepository == null) {
            accountRepository = new AccountRepositoryImpl();
        }
        return accountRepository;
    }

    public CurrencyRepository getCurrencyRepository() {
        if (currencyRepository == null) {
            currencyRepository = new CurrencyRepositoryImpl();
        }
        return currencyRepository;
    }
}
