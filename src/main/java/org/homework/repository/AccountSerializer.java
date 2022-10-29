package org.homework.repository;


import org.homework.annotation.Service;
import org.homework.entity.Account;

@Service
public class AccountSerializer extends SerializerAbstract<Account> {

    public AccountSerializer(){
        super(Account[].class, "accounts.json");
        System.out.println("constructor AccSerial");
    }

}
