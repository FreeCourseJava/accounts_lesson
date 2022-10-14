package org.homework.entity;

import java.util.Objects;

public class Currency implements HasKey {
    
    public double rateToUsd;
    public String abbrev;

    @Override
    public String toString() {
        return "Currency{" +
                "rateToUsd=" + rateToUsd +
                ", abbrev='" + abbrev + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Double.compare(currency.rateToUsd, rateToUsd) == 0 && Objects.equals(abbrev, currency.abbrev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateToUsd, abbrev);
    }

    @Override
    public String getKey() {
        return abbrev;
    }
}
