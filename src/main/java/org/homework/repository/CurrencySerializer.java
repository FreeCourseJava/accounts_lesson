package org.homework.repository;

import org.homework.annotation.Service;
import org.homework.entity.Currency;

@Service
public class CurrencySerializer extends SerializerAbstract<Currency> {

    public CurrencySerializer(){
        super(Currency[].class, "currencies.json");
    }

}
