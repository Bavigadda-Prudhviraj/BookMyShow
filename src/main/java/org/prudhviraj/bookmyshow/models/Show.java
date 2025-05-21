package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.Features;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Setter
@Getter
//Builder
@Entity(name = "shows")
public class Show extends BaseModel{
    /*
    1 -------> 1
    Show - Movie
    M <------- 1
     */
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    /*
    1 -------> 1
    Show - Screen
    M <------- 1
     */
    @ManyToOne
    private Screen screen;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    List<Features> features;

}
