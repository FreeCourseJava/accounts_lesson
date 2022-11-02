package org.homework.service;

public interface CurrencyService {

    double convert(Long from, Long to, double amount);

    double convert(String from, String to, double amount);

}
