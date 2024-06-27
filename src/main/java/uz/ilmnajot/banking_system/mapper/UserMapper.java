package uz.ilmnajot.banking_system.mapper;

import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.model.request.RegisterRequest;

public interface UserMapper {

    User toUser(RegisterRequest request);
}
