package org.homework;

import org.homework.controller.AccountController;
import org.homework.factory.GeneralFactory;

public class Main {

    public static void main(String[] args) {

        AccountController accountController = GeneralFactory.getInstance().getControllerFactory().getAccountController();
        // do work
        accountController.receiveCommand();
    }
}