package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.SeatType;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
//Builder
@Entity
public class Seat extends BaseModel {
    @Column(name = "'row'")
    private int row;
    @Column(name = "'column'")
    private int column;
    private String seatNumber;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
