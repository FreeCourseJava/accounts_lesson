package org.homework.service;

import org.homework.entity.AccountTransactionRequest;
import org.homework.entity.AccountTransactionResponse;

public interface AccountService {

    AccountTransactionResponse doTransaction(AccountTransactionRequest accountTransactionRequest);

}
