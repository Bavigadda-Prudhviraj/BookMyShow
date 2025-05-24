package org.prudhviraj.bookmyshow.dtos;

import lombok.Data;

@Data
public class SignUpResponseDto {
    private Long userId;
    private ResponseStatus responseStatus;
}
