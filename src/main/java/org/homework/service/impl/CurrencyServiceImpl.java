package org.homework.service.impl;

import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {

    private static final String BASE_CURRENCY = "USD";
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public double convert(String from, String to, double amount) {
        Currency currencyFrom = currencyRepository.load(from);
        if (to.equals(BASE_CURRENCY)) {
            return amount / currencyFrom.rateToUsd;
        }
        Currency currencyTo = currencyRepository.load(to);

        return amount / currencyFrom.rateToUsd * currencyTo.rateToUsd;
    }
}
