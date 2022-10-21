package org.homework.repository.impl;

import org.homework.di.annotation.Service;
import org.homework.entity.Account;
import org.homework.repository.AccountRepository;
@Service
public class AccountRepositoryImpl extends AbstractRepository<Account> implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account[].class, "accounts.json");
    }
}
