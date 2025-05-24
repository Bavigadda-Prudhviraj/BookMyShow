package org.prudhviraj.bookmyshow.services;

import lombok.AllArgsConstructor;
import org.prudhviraj.bookmyshow.exceptions.InvalidCredentialsException;
import org.prudhviraj.bookmyshow.exceptions.UserAlreadyExistsException;
import org.prudhviraj.bookmyshow.exceptions.UserNotFoundException;
import org.prudhviraj.bookmyshow.models.User;
import org.prudhviraj.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User signUp(String name, String email, String password) throws UserAlreadyExistsException {
        /// 1. Check if a user already exists with the provided email
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        /// 2. If yes, ask the user to log in
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
        }
        ///  2. If not, create a new user
        User user = User.builder()
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();

        /// 3. Return user
        return userRepository.save(user);
    }

    public User login(String email, String rawPassword) throws UserNotFoundException, InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        if (!passwordEncoder.matches(rawPassword, userOptional.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid password for user: " + email);
        }
        return userOptional.get();
    }
}
