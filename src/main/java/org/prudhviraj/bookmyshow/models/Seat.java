package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.SeatType;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
//Builder
@Entity
public class Seat extends BaseModel {
    private int row;
    private int column;
    private String seatNumber;
    @Enumerated(EnumType.ORDINAL)
    private SeatType type;
}
