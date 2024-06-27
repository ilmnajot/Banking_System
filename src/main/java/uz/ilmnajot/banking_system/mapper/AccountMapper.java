package uz.ilmnajot.banking_system.mapper;

import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.model.response.AccountResponse;

public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);
}
