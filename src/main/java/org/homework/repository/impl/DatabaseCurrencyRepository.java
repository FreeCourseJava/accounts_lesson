package org.homework.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.homework.di.annotation.Service;
import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;
import org.homework.repository.DatabaseConnector;

@Service
public class DatabaseCurrencyRepository implements CurrencyRepository {

    private final DatabaseConnector databaseConnector;

    public DatabaseCurrencyRepository(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Currency load(Long id) {
        String sql = "SELECT * FROM currency WHERE currency_id = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        return databaseConnector.selectPrepared(sql, params, this::map);
    }

    @Override
    public Currency load(String key) {
        String sql = "SELECT * FROM currency WHERE currency_abbrev = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, key);
        return databaseConnector.selectPrepared(sql, params, this::map);
    }

    @Override
    public void save(Currency entity) {
        String sql = "UPDATE currency SET currency_abbrev=?, rate=? WHERE currency_id=?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, entity.getAbbrev());
        params.put(2, entity.getRateToUsd());
        params.put(3, entity.getCurrencyId());
        databaseConnector.executePrepared(sql, params);
    }

    @Override
    public Collection<Currency> getAll() {
        String sql = "SELECT * FROM currency";
        Map<Integer, Object> params = new HashMap<>();
        return databaseConnector.selectPrepared(sql, params, rs -> {
            List<Currency> list = new ArrayList<>();
            list.add(map(rs));
            return list;
        });
    }

    private Currency map(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Currency currency = new Currency();
                currency.setCurrencyId(resultSet.getLong("currency_id"));
                currency.setAbbrev(resultSet.getString("currency_abbrev"));
                currency.setRateToUsd(resultSet.getDouble("rate"));
                return currency;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
