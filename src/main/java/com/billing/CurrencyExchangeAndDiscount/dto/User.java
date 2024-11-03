package com.billing.CurrencyExchangeAndDiscount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;
    private String name;
    private String userType;
    private LocalDate registrationDate;

}
