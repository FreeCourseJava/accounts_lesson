package org.homework.repository.impl;

import org.homework.entity.Account;
import org.homework.repository.AccountRepository;

public class AccountRepositoryImpl extends AbstractRepository<Account> implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account[].class, "accounts.json");
    }
}
