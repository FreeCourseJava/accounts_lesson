package org.homework;

import org.homework.controller.AccountController;
import org.homework.controller.impl.AccountControllerImpl;
import org.homework.repository.AccountRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.repository.impl.AccountRepositoryImpl;
import org.homework.repository.impl.CurrencyRepositoryImpl;
import org.homework.service.AccountService;
import org.homework.service.CurrencyService;
import org.homework.service.impl.AccountServiceImpl;
import org.homework.service.impl.CurrencyServiceImpl;

public class Main {
    
    public static void main(String[] args) {
        
        // prepare service
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
        CurrencyService currencyService = new CurrencyServiceImpl(currencyRepository);
        AccountRepository accountRepository = new AccountRepositoryImpl();
        AccountService accountService = new AccountServiceImpl(currencyService, accountRepository);
        AccountController accountController = new AccountControllerImpl(accountService);
        
        // do work
        accountController.receiveCommand();
    }
}