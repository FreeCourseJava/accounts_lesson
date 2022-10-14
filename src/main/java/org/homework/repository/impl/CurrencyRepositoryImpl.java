package org.homework.repository.impl;


import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;

public class CurrencyRepositoryImpl extends AbstractRepository<Currency> implements CurrencyRepository {

    private static CurrencyRepositoryImpl instance;

    private CurrencyRepositoryImpl() {
        super(Currency[].class, "currencies.json");
    }

    public static CurrencyRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CurrencyRepositoryImpl();
        }
        return instance;
    }
}
