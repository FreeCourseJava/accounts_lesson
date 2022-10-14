package org.homework.repository.impl;

import org.homework.entity.Account;
import org.homework.repository.AccountRepository;

public class AccountRepositoryImpl extends AbstractRepository<Account> implements AccountRepository {

    private static AccountRepositoryImpl instance;

    private AccountRepositoryImpl() {
        super(Account[].class, "accounts.json");
    }

    public static AccountRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AccountRepositoryImpl();
        }
        return instance;
    }
}
