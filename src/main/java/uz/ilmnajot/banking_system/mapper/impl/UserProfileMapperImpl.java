package uz.ilmnajot.banking_system.mapper.impl;

import org.springframework.stereotype.Component;
import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.mapper.UserProfileMapper;
import uz.ilmnajot.banking_system.model.response.UserProfileResponse;

@Component
public class UserProfileMapperImpl implements UserProfileMapper {


    @Override
    public UserProfileResponse toUserProfile(User user) {
        return UserProfileResponse
                .builder()
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }
}
