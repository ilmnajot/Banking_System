package uz.ilmnajot.banking_system.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.enums.Role;
import uz.ilmnajot.banking_system.model.request.RegisterRequest;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;


    @Override
    public User toUser(RegisterRequest request) {
        return User
                .builder()
                .name(request.getName())
                .role(Role.USER)
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}
