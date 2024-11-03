package com.billing.CurrencyExchangeAndDiscount.service;

import com.billing.CurrencyExchangeAndDiscount.dto.CurrencyConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final String API_KEY = "889c3fc2131423705f3ee628";
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    @Autowired
    private RestTemplate restTemplate;

    public Double getCurrentExchangeRate(String originalCurrency, String targetCurrency) {
        String urlStr = BASE_URL + API_KEY + "/latest/" + originalCurrency;
        CurrencyConversionResponse response;
        URL url = null;
        try {
            url = new URL(urlStr);
            response = restTemplate.getForObject(url.toURI(), CurrencyConversionResponse.class);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Map<String, Double> currentConversionRate = response.getConversion_rates();
        return currentConversionRate.get(targetCurrency);
    }
}
