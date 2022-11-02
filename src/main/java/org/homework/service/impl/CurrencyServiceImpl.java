package org.homework.service.impl;

import org.homework.di.annotation.Service;
import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    
    private final CurrencyRepository currencyRepository;
    
    private static final String BASE_CURRENCY = "USD";

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public double convert(Long from, Long to, double amount) { 
        return convert(currencyRepository.load(from).getAbbrev(), currencyRepository.load(to).getAbbrev(), amount);
    }
    @Override
    public double convert(String from, String to, double amount) {
        Currency currencyFrom = currencyRepository.load(from);
        if (to.equals(BASE_CURRENCY)) {
            return amount / currencyFrom.getRateToUsd();
        }
        Currency currencyTo = currencyRepository.load(to);
        
        return amount / currencyFrom.getRateToUsd() * currencyTo.getRateToUsd();
    }
}
