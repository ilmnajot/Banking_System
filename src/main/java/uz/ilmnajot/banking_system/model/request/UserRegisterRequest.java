package uz.ilmnajot.banking_system.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterRequest {

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message="mobile phone number is required")
    private String phone;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 5, max = 32, message = "password size should be between 5 and 32 digit or character")
    private String password;
}

