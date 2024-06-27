package uz.ilmnajot.banking_system.mapper;

import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.model.response.UserProfileResponse;

public interface UserProfileMapper {

    UserProfileResponse toUserProfile(User user);
}
