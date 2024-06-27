package uz.ilmnajot.banking_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.banking_system.model.ApiResponse;
import uz.ilmnajot.banking_system.service.UserService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getUserProfile(){
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(userService.getUserProfile())
                        .build());
    }

}
