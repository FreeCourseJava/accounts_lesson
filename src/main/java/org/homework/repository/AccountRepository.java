package org.homework.repository;

import org.homework.entity.Account;

public interface AccountRepository {
    
    Account getAccountByAccountName(String accountName);
    
}
