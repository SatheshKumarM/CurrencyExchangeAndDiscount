package com.billing.CurrencyExchangeAndDiscount.service;

import com.billing.CurrencyExchangeAndDiscount.dto.BillDetails;
import com.billing.CurrencyExchangeAndDiscount.dto.BillSummaryResponse;
import com.billing.CurrencyExchangeAndDiscount.dto.Product;
import com.billing.CurrencyExchangeAndDiscount.dto.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculateServiceTest {

    @InjectMocks
    private CalculateService calculateService;

    @Mock
    private CurrencyServiceImpl currencyService;

    @Test
    public void calculate_Success() {
        BillDetails billDetails = new BillDetails();
        billDetails.setBillId(1);
        billDetails.setOriginalCurrency("USD");
        billDetails.setTargetCurrency("EUR");
        List<Product> productDetails = new ArrayList<>();
        Product product = new Product();
        product.setProductId(1001);
        product.setProductName("Oil");
        product.setGrocery(true);
        product.setPrice(10.00);
        productDetails.add(product);
        billDetails.setProductDetails(productDetails);
        billDetails.setUserInfo(new User(9944, "John", "EMPLOYEE", LocalDate.of(2022, 11, 01)));
        when(currencyService.getCurrentExchangeRate(Mockito.anyString(),Mockito.anyString())).thenReturn(0.924);
        BillSummaryResponse response = calculateService.calculate(billDetails);
        assertEquals(1, response.getBillId());
        assertEquals(9944,response.getUserId());
    }

}
