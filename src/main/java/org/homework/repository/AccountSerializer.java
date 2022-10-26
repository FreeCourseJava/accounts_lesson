package org.homework.repository;


import org.homework.entity.Account;

public class AccountSerializer extends SerializerAbstract<Account> {

    public AccountSerializer(){
        super(Account[].class, "accounts.json");
    }

}
