package org.homework.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.homework.di.annotation.Service;
import org.homework.entity.Account;
import org.homework.repository.AccountRepository;
import org.homework.repository.DatabaseConnector;

@Service
public class DatabaseAccountRepository implements AccountRepository {

    private final DatabaseConnector databaseConnector;

    public DatabaseAccountRepository(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Account load(Long id) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        return databaseConnector.selectPrepared(sql, params, this::map);
    }

    @Override
    public Account load(String key) {
        String sql = "SELECT * FROM accounts WHERE account_name = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, key);
        return databaseConnector.selectPrepared(sql, params, this::map);
    }

    @Override
    public void save(Account entity) {
        String sql = "UPDATE accounts SET account_name=?, currency_id=?, balance=? WHERE account_id=?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getAccountName());
        params.put(2, entity.getCurrencyId());
        params.put(3, entity.getBalance());
        params.put(4, entity.getAccountId());
        databaseConnector.executePrepared(sql, params);
    }

    @Override
    public Collection<Account> getAll() {
        String sql = "SELECT * FROM accounts";
        Map<Integer, Object> params = new HashMap<>();
        return databaseConnector.selectPrepared(sql, params, rs -> {
            List<Account> list = new ArrayList<>();
            list.add(map(rs));
            return list;
        });
    }

    private Account map(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getLong("account_id"));
                account.setAccountName(resultSet.getString("account_name"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCurrencyId(resultSet.getLong("currency_id"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

}
