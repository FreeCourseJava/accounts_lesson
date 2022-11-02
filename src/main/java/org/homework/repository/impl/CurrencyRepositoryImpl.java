package org.homework.repository.impl;


import org.homework.di.annotation.Service;
import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;

public class CurrencyRepositoryImpl extends AbstractRepository<Currency> implements CurrencyRepository {

    public CurrencyRepositoryImpl() {
        super(Currency[].class, "currencies.json");
    }
}
