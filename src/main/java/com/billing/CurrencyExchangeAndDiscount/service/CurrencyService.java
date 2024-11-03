package com.billing.CurrencyExchangeAndDiscount.service;

public interface CurrencyService {

    Double getCurrentExchangeRate(String originalCurrency, String targetCurrency);
}
