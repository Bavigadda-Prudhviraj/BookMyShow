package org.prudhviraj.bookmyshow.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookings;
}

