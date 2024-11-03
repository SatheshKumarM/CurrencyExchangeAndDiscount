Project Title
Currency Exchange and Discount Calculator

Project Description
This project provides endpoint : api/v1/calculate for calculating currency exchange rates and applying discounts to bills. It Uses the exchangerate-api for real-time currency conversions and a custom discount calculator to adjust bill amounts.


openexchangerates.api.key=889c3fc2131423705f3ee628


POST : api/v1/calculate

{
  "billId": 123,
  "productDetails": [
    {
      "productId": 1,
      "productName": "Oil",
      "isGrocery": true,
      "price": 90.0
    }
  ],
  "userInfo": {
    "userId": 45,
    "name": "William",
    "userType": "EMPLOYEE",   
    "registrationDate": "2024-11-03"
  },
  "originalCurrency": "USD",
  "targetCurrency": "EUR"
}