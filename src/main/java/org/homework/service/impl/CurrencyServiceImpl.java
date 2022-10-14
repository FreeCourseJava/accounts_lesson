package org.homework.service.impl;

import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;
import org.homework.repository.impl.CurrencyRepositoryImpl;
import org.homework.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {

    private static final String BASE_CURRENCY = "USD";
    private static CurrencyServiceImpl instance;
    private final CurrencyRepository currencyRepository;

    private CurrencyServiceImpl() {
        this.currencyRepository = CurrencyRepositoryImpl.getInstance();
    }

    public static CurrencyServiceImpl getInstance() {
        if (instance == null) {
            instance = new CurrencyServiceImpl();
        }
        return instance;
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
