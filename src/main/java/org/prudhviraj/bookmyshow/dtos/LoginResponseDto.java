package org.prudhviraj.bookmyshow.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prudhviraj.bookmyshow.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    User user;
    ResponseStatus responseStatus;
}
