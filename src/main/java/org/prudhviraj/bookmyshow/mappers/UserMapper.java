package org.prudhviraj.bookmyshow.mappers;


import org.prudhviraj.bookmyshow.dtos.ResponseStatus;
import org.prudhviraj.bookmyshow.dtos.SignUpResponseDto;
import org.prudhviraj.bookmyshow.dtos.SignupRequestDto;
import org.prudhviraj.bookmyshow.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(SignupRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword()) // Note: Password should be encrypted in service layer
                .build();
    }

    public SignUpResponseDto toSignUpResponseDto(User user) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        responseDto.setUserId(user.getId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
}