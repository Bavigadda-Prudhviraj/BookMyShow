package org.prudhviraj.bookmyshow.models;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
//@Builder
@Entity
public class Region extends BaseModel {
    private String name;
    //private List<Theater> theaters; // we have a region in theater class
}
