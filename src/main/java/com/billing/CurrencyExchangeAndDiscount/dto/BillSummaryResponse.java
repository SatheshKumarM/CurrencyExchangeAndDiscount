package com.billing.CurrencyExchangeAndDiscount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillSummaryResponse {

    private int billId;
    private int userId;
    private LocalDate responseTime;
    private Double total;

}
