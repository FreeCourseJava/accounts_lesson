package org.homework.factory;

import org.homework.controller.AccountController;
import org.homework.controller.impl.AccountControllerImpl;

public class ControllerFactory {

    private AccountController accountController;

    ControllerFactory() {
    }

    public AccountController getAccountController() {
        if (accountController == null) {
            accountController = new AccountControllerImpl(GeneralFactory.getInstance().getServiceFactory().getAccountService());
        }
        return accountController;
    }
}
