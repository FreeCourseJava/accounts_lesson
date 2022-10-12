package org.homework.service;

import org.homework.entity.Currency;

public interface CurrencyService {
    
    double convert(Currency from, Currency to, double amount);
    
}
