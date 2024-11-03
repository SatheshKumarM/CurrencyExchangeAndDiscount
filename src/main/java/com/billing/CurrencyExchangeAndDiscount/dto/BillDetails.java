package com.billing.CurrencyExchangeAndDiscount.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetails {

    private int billId;
    private List<Product> productDetails;
    private User userInfo;
    private String originalCurrency;
    private String targetCurrency;

}
