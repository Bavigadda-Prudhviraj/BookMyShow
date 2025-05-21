package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.*;

import jakarta.persistence.*;
import lombok.*;



@Setter
@Getter
//@Builder
@Entity
public class Payment extends BaseModel {
    private String referenceNumber;
    private String paymentId;
    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider Provider;
    private PaymentMode Mode;
    private PaymentStatus Status;
}
