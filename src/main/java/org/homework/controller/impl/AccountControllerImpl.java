package org.homework.controller.impl;

import org.homework.annotation.Service;
import org.homework.annotation.StartPoint;
import org.homework.controller.AccountController;
import org.homework.service.AccountService;

import java.util.InputMismatchException;
import java.util.Scanner;

@StartPoint
public class AccountControllerImpl implements AccountController {



    @Override
    public void receiveCommand() {
        Scanner scanner = new Scanner(System.in);
        String donor, acceptor;
        double sum;

        System.out.println("Система платежей");
        System.out.println("Введите имя отправителя");
        donor = scanner.nextLine();
        System.out.println("Введите имя получателя");
        acceptor = scanner.nextLine();
        try{
        System.out.println("Введите сумму в рублях");
        sum = scanner.nextInt();}
        catch (InputMismatchException e){
            System.out.println("Неверная сумма");
            return;
        }

        AccountService accServ = new AccountService();
        accServ.cashTranslation(donor,acceptor,sum);


    }

}
