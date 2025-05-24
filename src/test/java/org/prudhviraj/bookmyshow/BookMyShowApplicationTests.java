package org.prudhviraj.bookmyshow;

import org.junit.jupiter.api.Test;
import org.prudhviraj.bookmyshow.controllers.UserController;
import org.prudhviraj.bookmyshow.dtos.LoginRequestDto;
import org.prudhviraj.bookmyshow.dtos.LoginResponseDto;
import org.prudhviraj.bookmyshow.dtos.SignUpResponseDto;
import org.prudhviraj.bookmyshow.dtos.SignupRequestDto;
import org.prudhviraj.bookmyshow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowApplicationTests {
    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
    }
    @Test
    void signup() {
        SignupRequestDto request = new SignupRequestDto();
        request.setName("Prudhviraj");
        request.setEmail("raj@example.com");
        request.setPassword("password");

        SignUpResponseDto response = userController.signup(request);
        System.out.println(response);
    }
    @Test
    void login()  {
        // Arrange
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("prudhviraj@example.com");
        request.setPassword("password");


        LoginResponseDto response = userController.login(request);


        User user = response.getUser();

        // Print full user details (for debug)
        System.out.println("User Details:");
        System.out.println("Name     : " + user.getName());
        System.out.println("Email    : " + user.getEmail());
        System.out.println("Password : " + user.getPassword()); // Note: this is hashed!
        System.out.println("User ID  : " + user.getId());
        System.out.println("User logged in successfully");
    }

}
