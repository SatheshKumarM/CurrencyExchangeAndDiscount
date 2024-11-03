package com.billing.CurrencyExchangeAndDiscount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionResponse {

        private String result;
        private String base_code;
        private Map<String, Double> conversion_rates;

}
