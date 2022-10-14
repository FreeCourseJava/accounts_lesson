package org.homework;

import org.homework.controller.AccountController;
import org.homework.controller.impl.AccountControllerImpl;

public class Main {

    public static void main(String[] args) {

        AccountController accountController = AccountControllerImpl.getInstance();

        // do work
        accountController.receiveCommand();
    }
}