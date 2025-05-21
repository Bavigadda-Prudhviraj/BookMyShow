package org.prudhviraj.bookmyshow.models.enums;

public enum PaymentMode {
    UPI,
    DEBIT_CARD,
    CREDIT_CARD,
    NET_BANKING,
    WALLET,
    BANK_TRANSFER,
    CASH,             // For offline bookings/events
    EMI,              // Installment payment mode
    CARD_ON_DELIVERY, // For ticket deliveries or offline events
    QR_CODE,          // Scan to pay via apps like GPay/PhonePe
    PAY_LATER         // Deferred payments (via Klarna/AfterPay)
}
