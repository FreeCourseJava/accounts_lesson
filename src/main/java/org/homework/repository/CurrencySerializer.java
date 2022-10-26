package org.homework.repository;

import org.homework.entity.Currency;

public class CurrencySerializer extends SerializerAbstract<Currency> {

    public CurrencySerializer(){
        super(Currency[].class, "currencies.json");
    }

}
