package org.prudhviraj.bookmyshow.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
//Builder
@Entity
public class Theater extends BaseModel {

    private String name;
    @ManyToOne
    private Region region;
    @OneToMany
    private List<Screen> screens;

}
