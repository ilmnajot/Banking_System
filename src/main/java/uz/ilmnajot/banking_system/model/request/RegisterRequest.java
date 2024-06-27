package uz.ilmnajot.banking_system.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "phone number is required")
    private String phone;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 5, max = 32, message = "password size should be between 5 and 32 digit or characcter")
    private String password;


}
