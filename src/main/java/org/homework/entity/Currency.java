package org.homework.entity;

import java.util.Objects;

public class Currency implements HasKey {
    
    private Long currencyId;
    private double rateToUsd;
    private String abbrev;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public double getRateToUsd() {
        return rateToUsd;
    }

    public void setRateToUsd(double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId=" + currencyId +
                ", rateToUsd=" + rateToUsd +
                ", abbrev='" + abbrev + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Double.compare(currency.rateToUsd, rateToUsd) == 0 && Objects.equals(currencyId, currency.currencyId) && Objects.equals(abbrev, currency.abbrev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, rateToUsd, abbrev);
    }

    @Override
    public String getKey() {
        return abbrev;
    }

    @Override
    public Long getId() {
        return currencyId;
    }
}
