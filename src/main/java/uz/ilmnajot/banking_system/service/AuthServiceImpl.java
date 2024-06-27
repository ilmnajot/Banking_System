package uz.ilmnajot.banking_system.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.mapper.UserMapper;
import uz.ilmnajot.banking_system.model.request.LoginRequest;
import uz.ilmnajot.banking_system.model.request.RegisterRequest;
import uz.ilmnajot.banking_system.model.response.AuthResponse;
import uz.ilmnajot.banking_system.repository.UserRepository;
import uz.ilmnajot.banking_system.security.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (isEmailOrPhoneAlreadyExists(request.getEmail(), request.getPhone())) {
            throw new EntityExistsException("Email or Phone is already exists");
        }
        User user = userRepository.save(userMapper.toUser(request));
        return AuthResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User " + request.getEmail() + " Not Found"));
        return AuthResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    private boolean isEmailOrPhoneAlreadyExists(String email, String phone) {
        return userRepository.existsByEmail(email) || userRepository.existsByPhone(phone);
    }
}
