package org.homework.service;

import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;

public class CurrencyService {
    private static final String BASE_CURRENCY = "USD";


    public static double convert(String from, String to, double amount) {
        Currency currencyFrom = CurrencyRepository.load(from);
        if (to.equals(BASE_CURRENCY)) {
            return amount / currencyFrom.rateToUsd;
        }
        Currency currencyTo = CurrencyRepository.load(to);

        return amount / currencyFrom.rateToUsd * currencyTo.rateToUsd;
    }
}
