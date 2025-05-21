package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.Features;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Getter
@Setter
//@Builder
@Entity
public class Movie extends BaseModel {

    private String name;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    /**
     * One movie can have many shows across different theaters.
     * This should be mapped by a 'movie' field in the Show entity.
     */
    @OneToMany//(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;

    /**
     * Features like IMAX, 3D, Dolby, etc.
     * Stored as a separate table with movie_id and feature.
     */

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection//(fetch = FetchType.EAGER)
    private List<Features> features;
}
