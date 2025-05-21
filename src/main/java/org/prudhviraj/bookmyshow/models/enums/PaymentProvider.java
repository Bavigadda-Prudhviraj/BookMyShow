package org.prudhviraj.bookmyshow.models.enums;

public enum PaymentProvider {
    PAYPAL,
    STRIPE,
    RAZORPAY,
    PAYTM,
    GOOGLE_PAY,
    PHONEPE,
    AMAZON_PAY,
    APPLE_PAY,
    SAMSUNG_PAY,
    PAYU,
    KLARNA,
    AFTER_PAY,
    SQUARE,          // Widely used in retail and POS
    CASH_FREE,        // Indian payment gateway
    CCAVENUE,        // Legacy Indian provider
    INSTAMOJO,       // Indian micro-payment provider
    OTHER            // Fallback for unknown/custom providers
}

