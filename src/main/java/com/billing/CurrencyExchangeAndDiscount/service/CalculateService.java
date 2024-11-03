package com.billing.CurrencyExchangeAndDiscount.service;

import com.billing.CurrencyExchangeAndDiscount.dto.BillDetails;
import com.billing.CurrencyExchangeAndDiscount.dto.BillSummaryResponse;
import com.billing.CurrencyExchangeAndDiscount.dto.Product;
import com.billing.CurrencyExchangeAndDiscount.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculateService {

    @Autowired
    private CurrencyService currencyService;

    public BillSummaryResponse calculate(BillDetails billDetails) {
        Double groceryTotal = 0.00;
        Double nonGroceryTotal = 0.00;
        //Filter the Grocery and NonGrocery items separate
        for (Product product : billDetails.getProductDetails()) {
            if (product.isGrocery()) {
                groceryTotal += product.getPrice();
            } else {
                nonGroceryTotal += product.getPrice();
            }
        }
        //Calculate the Discount based on User Type
        Double nonGroceryTotalAfterDiscount = calculateDiscountBasedOnUserType(billDetails.getUserInfo(), nonGroceryTotal);
        Double totalBillBeforeDiscount = nonGroceryTotalAfterDiscount + groceryTotal;
        Double finalTotal = calculateFinalDiscount(totalBillBeforeDiscount);
        //Call Currency Service to get the current Exchange Rate
        Double currentExchangeRate = currencyService.getCurrentExchangeRate(billDetails.getOriginalCurrency(), billDetails.getTargetCurrency());
        return new BillSummaryResponse(billDetails.getBillId(), billDetails.getUserInfo().getUserId(), LocalDate.now(), finalTotal * currentExchangeRate);

    }

    public Double calculateDiscountBasedOnUserType(User userInfo, Double nonGroceryTotal) {
        if ("EMPLOYEE".equals(userInfo.getUserType())) {
            return nonGroceryTotal * (0.7); //30% Discount in case of Employee
        } else if ("AFFILIATE".equals(userInfo.getUserType())) {
            return nonGroceryTotal * (0.9); //10% Discount in case of Affiliate
        } else if (userInfo.getRegistrationDate() != null && ChronoUnit.YEARS.between(userInfo.getRegistrationDate(), LocalDate.now()) > 2) {
            return nonGroceryTotal * (0.95); //5% in case the customer is more than 2 years
        }
        return nonGroceryTotal;
    }

    public Double calculateFinalDiscount(Double total) {
        Integer discountUnits = (int) (total / 100);
        return total - (discountUnits * 5);
    }

}
