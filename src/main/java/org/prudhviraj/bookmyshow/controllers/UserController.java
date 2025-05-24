package org.prudhviraj.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import org.prudhviraj.bookmyshow.dtos.*;
import org.prudhviraj.bookmyshow.exceptions.InvalidCredentialsException;
import org.prudhviraj.bookmyshow.exceptions.UserAlreadyExistsException;
import org.prudhviraj.bookmyshow.exceptions.UserNotFoundException;
import org.prudhviraj.bookmyshow.mappers.UserMapper;
import org.prudhviraj.bookmyshow.models.User;
import org.prudhviraj.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    public SignUpResponseDto signup(SignupRequestDto request) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(request.getName(), request.getEmail(), request.getPassword());

            responseDto = userMapper.toSignUpResponseDto(user);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public LoginResponseDto login(LoginRequestDto request) {
        LoginResponseDto responseDto;
        try {
            User user = userService.login(request.getEmail(), request.getPassword());

            responseDto = new LoginResponseDto(user, ResponseStatus.SUCCESS);
        } catch (UserNotFoundException e) {
            responseDto = new LoginResponseDto(null, ResponseStatus.FAILURE);
            throw new RuntimeException("User not found with email: " + request.getEmail());
        } catch (InvalidCredentialsException e) {
            responseDto = new LoginResponseDto(null, ResponseStatus.FAILURE);
            throw new RuntimeException("Invalid password for user: " + request.getEmail());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseDto;
    }
}
