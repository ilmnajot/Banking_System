package uz.ilmnajot.banking_system.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.mapper.UserProfileMapper;
import uz.ilmnajot.banking_system.model.response.UserProfileResponse;
import uz.ilmnajot.banking_system.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse getUserProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User " + email + " not found"));
        return userProfileMapper.toUserProfile(user);
    }
}
