package org.prudhviraj.bookmyshow.models.enums;

public enum PaymentStatus {
    INITIATED,             // Payment request initiated
    IN_PROGRESS,           // User is completing the payment (e.g., redirect to gateway)
    PENDING,               // Awaiting response from a payment provider
    AUTHORIZED,            // Amount authorized but not captured (for pre-auth systems)
    CAPTURED,              // Amount captured (post-authorization)
    CONFIRMED,             // Payment fully successful and verified
    FAILED,                // Payment failed due to error or rejection
    DECLINED,              // Payment declined by bank/card/UPI
    CANCELLED,             // User or system canceled the payment
    EXPIRED,               // Payment session expired
    REFUNDED,              // Full refund processed
    PARTIALLY_REFUNDED,    // Partial refund processed
    CHARGEBACK,            // Disputed and reversed by user
    SETTLEMENT_PENDING,    // Awaiting bank settlement (e.g., T+1 transfer)
    SETTLED,               // Amount credited to merchant
    ON_HOLD,               // Payment flagged and temporarily held (e.g., fraud check)
    RETRYING,              // The System is retrying after a temporary failure
    UNDER_REVIEW           // Under manual/system review for suspicious activity
}

