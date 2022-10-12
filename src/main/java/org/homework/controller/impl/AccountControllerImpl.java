package org.homework.controller.impl;

import org.homework.controller.AccountController;
import org.homework.service.AccountService;

public class AccountControllerImpl implements AccountController {
    
    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void receiveCommand() {
        // You controller logic here
    }
}
