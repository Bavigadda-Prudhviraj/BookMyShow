package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
//Builder
@Entity
public class ShowSeatType extends BaseModel {
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private int price;
}
