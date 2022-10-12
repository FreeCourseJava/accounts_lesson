package org.homework.service.impl;

import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {
    
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public double convert(Currency from, Currency to, double amount) {
        // you convert logic here
        return 0;
    }
}
