package org.homework.service;


import org.homework.annotation.Service;
import org.homework.entity.Currency;
import org.homework.repository.CurrencySerializer;
@Service
public class CurrencyService {

    private CurrencySerializer currencySerializer;
    private Currency[] currencyRates;

    public CurrencyService(CurrencySerializer currencySerializer) {
        this.currencySerializer = currencySerializer;
        currencyRates = currencySerializer.read();
        System.out.println("constructor CurrServ");
    }

    public double convertToUSD(double sum, String abbrev) {
        double rate = -1;
        for (Currency curr : currencyRates) {
            if (curr.abbrev.equals(abbrev)) {
                rate = curr.rateToUsd;
            }
        }
        if (rate == -1) {
            System.out.println("Неизвестная валюта. Будет переведен 0 рублей");
            return 0;
        }
        return sum / rate;
    }

    public double convertFromUSD(double sum, String abbrev) {
        double rate = -1;
        for (Currency curr : currencyRates) {
            if (curr.abbrev.equals(abbrev)) {
                rate = curr.rateToUsd;
            }
        }
        if (rate == -1) {
            System.out.println("Неизвестная валюта. Будет переведен 0 рублей");
            return 0;
        }
        return sum * rate;
    }

}
