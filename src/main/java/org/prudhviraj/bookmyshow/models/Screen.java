package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.Features;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {

    private String name;

    /**
     1 -------> M
     Screen - Seat -- > 1:M
     1 <------- 1
     */
    @OneToMany///(mappedBy = "screen", cascade = CascadeType. ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;


    @ElementCollection//(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    private List<Features> features;
}
