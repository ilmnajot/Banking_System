package uz.ilmnajot.banking_system.service;

import uz.ilmnajot.banking_system.model.request.LoginRequest;
import uz.ilmnajot.banking_system.model.request.RegisterRequest;
import uz.ilmnajot.banking_system.model.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
