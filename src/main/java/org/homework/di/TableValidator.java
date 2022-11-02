package org.homework.di;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.homework.entity.Account;
import org.homework.entity.Currency;
import org.homework.repository.AccountRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.repository.DatabaseConnector;
import org.homework.repository.impl.AccountRepositoryImpl;
import org.homework.repository.impl.CurrencyRepositoryImpl;

public class TableValidator {
    
    private final DatabaseConnector databaseConnector;

    public TableValidator(DatabaseConnector databaseConnector) {
        if (databaseConnector == null) {
            throw new IllegalStateException("Database connector was not created");
        }
        this.databaseConnector = databaseConnector;
    }

    public void validate() {
        validateAccounts();
        validateCurrencies();
        insertDummyCurrency();
        insertDummyAccounts();
    }

    private void insertDummyAccounts() {
        int count = databaseConnector.select("SELECT COUNT(*) FROM accounts", rs -> {
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        if (count == 0) {
            insertAccounts();
        }
    }

    private void insertAccounts() {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        Collection<Account> accounts = accountRepository.getAll();
        String sql = "INSERT INTO accounts (account_name, currency_id, balance) SELECT ?, currency_id, ? FROM currency WHERE currency_abbrev=? ";
        for (Account account : accounts) {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, account.getAccountName());
            params.put(2, account.getBalance());
            params.put(3, account.getCurrencyAbbrev());
            databaseConnector.executePrepared(sql, params);
        }
    }

    private void insertDummyCurrency() {
        
        int count = databaseConnector.select("SELECT COUNT(*) FROM currency", rs -> {
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        if (count == 0) {
            insertCurrency();
        }
    }

    private void insertCurrency() {
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
        Collection<Currency> currencies = currencyRepository.getAll();
        for (Currency currency : currencies) {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, currency.getAbbrev());
            params.put(2, currency.getRateToUsd());
            databaseConnector.executePrepared("INSERT INTO currency (currency_abbrev, rate) VALUES (?, ?)", params);
        }
    }

    private void validateCurrencies() {
        try {
            databaseConnector.select("SELECT * FROM currency", rs -> null);
        } catch (RuntimeException runtimeException) {
            if (runtimeException.getCause() instanceof SQLSyntaxErrorException) {
                if (runtimeException.getMessage().contains("Unknown database")) {
                    throw new RuntimeException("Unable to connect to database! Please create database accounts_lesson");
                } else if (runtimeException.getMessage().contains("accounts_lesson.currency")) {
                    createCurrencyTable();
                } else {
                    throw new RuntimeException("Fatal error on validate database");
                }
            }
        }
    }

    private void createCurrencyTable() {
        String sql = "CREATE TABLE `accounts_lesson`.`currency` ( " +
                "  `currency_id` BIGINT(20) NOT NULL AUTO_INCREMENT, " +
                "  `currency_abbrev` VARCHAR(45) NOT NULL, " +
                "  `rate` DOUBLE NOT NULL, " +
                "  PRIMARY KEY (`currency_id`), " +
                "  UNIQUE INDEX `currency_abbrev_UNIQUE` (`currency_abbrev` ASC) VISIBLE)";
        databaseConnector.execute(sql);
    }

    private void validateAccounts() {
        try {
            databaseConnector.select("SELECT * FROM accounts", rs -> null);
        } catch (RuntimeException runtimeException) {
            if (runtimeException.getCause() instanceof SQLSyntaxErrorException) {
                if (runtimeException.getMessage().contains("Unknown database")) {
                    throw new RuntimeException("Unable to connect to database! Please create database accounts_lesson");
                } else if (runtimeException.getMessage().contains("accounts_lesson.accounts")) {
                    createAccountsTable();
                } else {
                    throw new RuntimeException("Fatal error on validate database");
                }
            }
        }
    }

    private void createAccountsTable() {
        String sql = "CREATE TABLE `accounts_lesson`.`accounts` (" +
                "  `account_id` BIGINT(20) NOT NULL AUTO_INCREMENT, " +
                "  `account_name` VARCHAR(45) NOT NULL, " +
                "  `currency_id` BIGINT(20) NOT NULL, " +
                "  `balance` DOUBLE NOT NULL, " +
                "  PRIMARY KEY (`account_id`), " +
                "  UNIQUE INDEX `account_name_UNIQUE` (`account_name` ASC) VISIBLE)";
        databaseConnector.execute(sql);
    }
}
