package uz.ilmnajot.banking_system.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.banking_system.model.ApiResponse;
import uz.ilmnajot.banking_system.model.request.LoginRequest;
import uz.ilmnajot.banking_system.model.request.RegisterRequest;
import uz.ilmnajot.banking_system.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .builder()
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .data(authService.register(request))
                        .build());
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid@ RequestBody LoginRequest request){
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(authService.login(request))
                        .build()
        );
    }
}
