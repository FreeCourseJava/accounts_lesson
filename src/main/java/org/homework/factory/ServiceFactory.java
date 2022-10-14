package org.homework.factory;

import org.homework.repository.AccountRepository;
import org.homework.service.AccountService;
import org.homework.service.CurrencyService;
import org.homework.service.impl.AccountServiceImpl;
import org.homework.service.impl.CurrencyServiceImpl;

public class ServiceFactory {

    private AccountService accountService;
    private CurrencyService currencyService;
    ServiceFactory() {
    }

    public AccountService getAccountService() {
        if (accountService == null) {
            AccountRepository accountRepository = GeneralFactory.getInstance().getRepositoryFactory().getAccountRepository();
            accountService = new AccountServiceImpl(getCurrencyService(), accountRepository);
        }
        return accountService;
    }

    public CurrencyService getCurrencyService() {
        if (currencyService == null) {
            currencyService = new CurrencyServiceImpl(GeneralFactory.getInstance().getRepositoryFactory().getCurrencyRepository());
        }
        return currencyService;
    }
}
