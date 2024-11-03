package com.billing.CurrencyExchangeAndDiscount.controller;

import com.billing.CurrencyExchangeAndDiscount.dto.BillDetails;
import com.billing.CurrencyExchangeAndDiscount.dto.BillSummaryResponse;
import com.billing.CurrencyExchangeAndDiscount.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

    @Autowired
    private CalculateService calculateService;

    @PostMapping("/calculate")
    public ResponseEntity<BillSummaryResponse> calculate(@RequestBody BillDetails billDetails) {
        BillSummaryResponse billSummaryResponse = calculateService.calculate(billDetails);
        return new ResponseEntity<>(billSummaryResponse, HttpStatus.OK);
    }


}
